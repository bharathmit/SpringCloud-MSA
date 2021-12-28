package com.jsoftgroup.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Arrays;
import java.util.List;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("jwtDecoderByIssuerUri")
    @Autowired
    JwtDecoder jwtDecoder;

    @Value("${client.registration}")
    private List<String> clientID;

    @Autowired
    private CustomExceptionEntryPoint customExceptionEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("------> inside SecurityConfiguration->configure method <-------");
        JwtDecoder newJwtDecoder = wrapJwtDecoderWithAudienceCheck(this.jwtDecoder, clientID);

        http
                .cors()
                .and()
                .authorizeRequests()
                .requestMatchers(EndpointRequest.to("info", "health")).permitAll()
                .antMatchers("/health","/actuator/**", "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2ResourceServer()
                .jwt( jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter() )
                .decoder(newJwtDecoder))
                .authenticationEntryPoint(customExceptionEntryPoint);

    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtConverter;
    }

    static JwtDecoder wrapJwtDecoderWithAudienceCheck(JwtDecoder jwtDecoder, List<String> clientIdList) {
        return (token) -> {
            Jwt jwt = jwtDecoder.decode(token);
            String callerClientId = jwt.getClaimAsString("azp");
            String matchedClientId = clientIdList.stream().
                    filter(p -> {
                        return p.contains(callerClientId);
                    }).
                    findAny().orElse(null);
            if (matchedClientId == null) {
                throw new JwtValidationException("The Client ID used(" + callerClientId + ") is not authenticated to access our service.", Arrays.asList(new OAuth2Error("invalid_appid")));
            }
            return jwt;
        };
    }
}
