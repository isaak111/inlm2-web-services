package com.example.oauthspringexe;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AuthenticationHandler
        extends SimpleUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler
{
    public AuthenticationHandler() {
        super();

        setUseReferer(false);
    }
}