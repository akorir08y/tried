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
    private final String mpesa_stk_request_url;
    private final String reports_url;
    private final String reconciliation_url;
    private final String dashboard_url;
    private final String payment_url;
}
