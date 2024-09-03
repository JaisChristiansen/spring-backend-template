package com.example.demo.security.interceptor;

import com.example.demo.util.StringUtil;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    private static String BEARER = "Bearer ";

    @Override
    public boolean preHandle(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull Object handler
    ) throws Exception {
        System.out.println("AccessTokenInterceptor preHandle ENTRY");

        String accessTokenHeader = request.getHeader("Authorization");
        if (StringUtil.isNullOrSpaces(accessTokenHeader) || !accessTokenHeader.startsWith(BEARER)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
