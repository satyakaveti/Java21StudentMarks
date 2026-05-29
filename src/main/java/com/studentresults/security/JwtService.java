package com.studentresults.security;

import org.springframework.stereotype.Service;

/**
 * JWT utility — generate, validate, and parse Bearer tokens.
 * Uses JJWT 0.12.x (io.jsonwebtoken).
 */
@Service
public class JwtService {

    // TODO: inject AppProperties for secret + expiry config

    public String generateAccessToken(UserPrincipal principal) {
        // TODO: implement using Jwts.builder()
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String generateRefreshToken(UserPrincipal principal) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String extractUsername(String token) {
        // TODO: implement using Jwts.parser()
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isTokenValid(String token, UserPrincipal principal) {
        // TODO: implement — check username match + expiry
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isTokenExpired(String token) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
