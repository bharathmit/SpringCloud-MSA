package com.jsoftgroup.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${app.api.logging.enable:true}")
public class ApiLoggingFilterConfig {

    @Value("${app.api.logging.url-patterns:*}")
    private String[] urlPatterns;

    @Bean
    public FilterRegistrationBean<ApiLoggingFilter> loggingFilter() {
        FilterRegistrationBean<ApiLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiLoggingFilter());
        registrationBean.addUrlPatterns(urlPatterns);
        registrationBean.setAsyncSupported(Boolean.TRUE);
        return registrationBean;
    }
}
