package com.example.tried.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="auth.login")
public class AuthConfiguration {

    private final String auth_login_url;
    private final String sms_url;
    private final String cfms_data;
    private final String financial_data;
    private final String trust_funds_url;
}
