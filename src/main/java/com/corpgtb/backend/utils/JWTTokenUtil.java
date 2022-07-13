package com.corpgtb.backend.utils;

import com.corpgtb.backend.model.dto.JWTUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JWTTokenUtil {

    private final String secret = "TTFDbGFWZVNlY3IzdGEyMDIw";
    private final String USR_CLAIM = "user";
    private final int JWT_TOKEN_VALIDITY = 30*60;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public JWTUser getUserFromToken(String token) {
        Gson gson = new Gson();
        JWTUser usuario = null;
        ObjectMapper mapper = new ObjectMapper();
        Object algo = null;
        String otroAlgo;
        try {
            final Claims claims = getAllClaimsFromToken(token);
            algo = claims.get(USR_CLAIM);
            otroAlgo = gson.toJson(algo);
            usuario = mapper.readValue(otroAlgo,JWTUser.class);
        } catch (Exception e) {
            e.printStackTrace();
            usuario = null;
        }
        return usuario;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generarToken(JWTUser user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(USR_CLAIM,user);
        return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String generarToken(JWTUser user, long validity) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(USR_CLAIM,user);
        return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token));
    }

    public String refreshToken(String token) {
        if (!canTokenBeRefreshed(token)) {
            return null;
        }
        try {
            final Claims claims = getAllClaimsFromToken(token);
            return Jwts.builder().setClaims(claims).setSubject(getUsernameFromToken(token)).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        } catch (Exception e) {
            return null;
        }
    }
}
