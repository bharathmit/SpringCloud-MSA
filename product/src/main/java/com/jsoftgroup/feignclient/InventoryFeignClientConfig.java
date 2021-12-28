package com.jsoftgroup.feignclient;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.util.Collection;
import java.util.Collections;

public class InventoryFeignClientConfig {

    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;


    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {

                OAuth2AuthorizedClient client = oAuth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("product-service")
                        .principal(createPrincipal()).build());

                template.header("Authorization", getAuthorizationToken(client.getAccessToken()));
            }
        };
    }

    private String getAuthorizationToken(OAuth2AccessToken accessToken) {
        System.out.println("Access Token: "+accessToken.getTokenValue());
        return String.format("%s %s", accessToken.getTokenType().getValue(), accessToken.getTokenValue());
    }

    private Authentication createPrincipal() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptySet();
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return this;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

            @Override
            public String getName() {
                return "product-service";
            }
        };
    }




}
