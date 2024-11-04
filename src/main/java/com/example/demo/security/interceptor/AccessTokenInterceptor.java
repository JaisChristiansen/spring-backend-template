package com.example.demo.security.interceptor;

import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.user.UserService;
import com.example.demo.util.AuthUtil;
import com.example.demo.util.StringUtil;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;
import java.util.UUID;

@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Override
    public boolean preHandle(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull Object handler
    ) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        String accessTokenHeader = request.getHeader("Authorization");
        if (StringUtil.isNullOrSpaces(accessTokenHeader) || !accessTokenHeader.startsWith(AuthUtil.BEARER)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        UUID userId = Optional.of(accessTokenHeader)
                .map(s -> s.replaceAll(AuthUtil.BEARER, ""))
                .map(jwtService::extractId)
                .map(jsonObject -> UUID.fromString(jsonObject.get("userId").getAsString()))
                .orElse(null);
        Optional<User> user = userService.getById(userId);
        return user.isPresent();
    }
}
