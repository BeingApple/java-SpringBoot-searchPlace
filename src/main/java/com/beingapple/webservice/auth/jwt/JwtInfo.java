package com.beingapple.webservice.auth.jwt;


import com.auth0.jwt.algorithms.Algorithm;

public class JwtInfo {
    public static final String HEADER_NAME = "Authorization";

    public static final String ISSUER = "beingapple";

    public static final String TOKEN_KEY = "com.beingapple.searchplace";

    public static final long EXPIRES_LIMIT = 3L;

    public static Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(JwtInfo.TOKEN_KEY);
        } catch (IllegalArgumentException e) {
            return Algorithm.none();
        }
    }
}
