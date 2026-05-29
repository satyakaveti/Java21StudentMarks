package com.studentresults.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Called after a successful Google OAuth2 login.
 * Creates or links the student account, then redirects with a JWT.
 */
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // TODO: inject UserRepository, JwtService, AppProperties

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // TODO: implement
        //  1. Extract OAuth2User from authentication
        //  2. Find or create User + Student linked to the Google account
        //  3. Generate JWT
        //  4. Redirect to frontend with token as query param

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
