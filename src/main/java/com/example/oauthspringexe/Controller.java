package com.example.oauthspringexe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    private final OAuth2AuthorizedClientService oauthService;

    @Autowired
    public Controller(
            OAuth2AuthorizedClientService oauthService
    ) {
        this.oauthService = oauthService;
    }

    @GetMapping("/")
    public String home (){

        return " <form action=/authorize> <input type=submit value=Click-to-authorize></form> " +
                "<form action=/authorized> <input type=submit value=Only-for-authorized></form>";
    }




   @GetMapping("/authorize")
    public Map<String, Object> user(
            @AuthenticationPrincipal OAuth2User principal,
            Authentication auth
    ) {
        var oauthToken = (OAuth2AuthenticationToken) auth;
        var client =
                oauthService.loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());

        System.out.println(client.getAccessToken().getTokenValue());
        return principal.getAttributes();
    }
    @GetMapping("/authorized")
    public String kek (){

        return "Welcome to Kekistan";
    }




}