package com.example.demo.service;

import com.example.demo.config.EnvironmentConfig;
import com.example.demo.model.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    EnvironmentConfig environmentConfig;

    @Autowired
    public JwtService(EnvironmentConfig environmentConfig) {
        this.environmentConfig = environmentConfig;
    }

    public long getExpirationTime() {
        return 1000*60*60*24*7;
    }

    public Long generateSession() {
        return Optional.of(new SecureRandom())
                .map(Random::nextLong)
                .orElse(null);
    }

    public String generateToken(User user, Boolean rememberMe, Long session) {
        return generateToken(user, rememberMe, session, new HashMap<>());
    }

    public String generateToken(User user, Boolean rememberMe, Long session, Map<String, Object> extraClaims) {
        Date createTime = new Date();
        Date expires = rememberMe != null && rememberMe ? new Date(System.currentTimeMillis() + getExpirationTime()) : null;
        JsonObject idJson = new JsonObject();
        idJson.addProperty("userId", String.valueOf(user.getId()));
        idJson.addProperty("session", Long.toHexString(session));

        return Jwts.builder()
                .id(idJson.toString())
                .claims(extraClaims)
                .subject(user.getMail())
                .issuedAt(createTime)
                .expiration(expires)
                .signWith(getSigningKey())
                .compact();
    }

    public boolean isTokenValid(String token, User user) {
        JsonObject id = extractId(token);
        UUID uuid = UUID.fromString(id.get("userID").getAsString());
        String session = id.get("session").getAsString();
        return user.getId().equals(uuid) && user.getSessionToken().equals(session) && !isTokenExpired(token);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public JsonObject extractId(String token) {
        String id = extractClaim(token, Claims::getId);
        return JsonParser.parseString(id).getAsJsonObject();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private SecretKey getSigningKey() {
        return new SecretKeySpec(environmentConfig.getSecretKey().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }
}
