package com.example.tried.config;





import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="auth.login")
public class AuthConfiguration {

    private final String auth_login_url;
    private final String sms_url;
    private final String cfms_data;
    private final String financial_data;
    private final String financial_data_https_url;
    private final String trust_funds_url;
    private final String mpesa_stk_request_url;
    private final String reports_url;
    private final String reconciliation_url;
    private final String dashboard_url;
    private final String payment_url;

    public String getAuth_login_url() {
        return auth_login_url;
    }

    public String getSms_url() {
        return sms_url;
    }

    public String getCfms_data() {
        return cfms_data;
    }

    public String getFinancial_data() {
        return financial_data;
    }

    public String getFinancial_data_https_url() {
        return financial_data_https_url;
    }

    public String getTrust_funds_url() {
        return trust_funds_url;
    }

    public String getMpesa_stk_request_url() {
        return mpesa_stk_request_url;
    }

    public String getReports_url() {
        return reports_url;
    }

    public String getReconciliation_url() {
        return reconciliation_url;
    }

    public String getDashboard_url() {
        return dashboard_url;
    }

    public String getPayment_url() {
        return payment_url;
    }

    public AuthConfiguration(String auth_login_url, String sms_url, String cfms_data, String financial_data, String financial_data_https_url, String trust_funds_url, String mpesa_stk_request_url, String reports_url, String reconciliation_url, String dashboard_url, String payment_url) {
        this.auth_login_url = auth_login_url;
        this.sms_url = sms_url;
        this.cfms_data = cfms_data;
        this.financial_data = financial_data;
        this.financial_data_https_url = financial_data_https_url;
        this.trust_funds_url = trust_funds_url;
        this.mpesa_stk_request_url = mpesa_stk_request_url;
        this.reports_url = reports_url;
        this.reconciliation_url = reconciliation_url;
        this.dashboard_url = dashboard_url;
        this.payment_url = payment_url;
    }
}
