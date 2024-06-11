package com.example.tried.dto.express;

import com.fasterxml.jackson.annotation.JsonProperty;



public class MpesaTransaction {

    @JsonProperty("invoice_id")
    private Integer invoice_id;

    @JsonProperty("payment_receipt_no")
    private String payment_receipt_no;

    @JsonProperty("paybill_number")
    private String paybill_number;

    @JsonProperty("payment_amount")
    private double payment_amount;

    @JsonProperty("transaction_date")
    private String transaction_date;

    @JsonProperty("institution_level")
    private String institution_level;

    @JsonProperty("institution_name")
    private String institution_name;

    @JsonProperty("institution_number")
    private String institution_number;

    @JsonProperty("phone_number")
    private String phone_number;

    @JsonProperty("checkout_code")
    private String checkout_code;

    public MpesaTransaction() {

    }

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getPayment_receipt_no() {
        return payment_receipt_no;
    }

    public void setPayment_receipt_no(String payment_receipt_no) {
        this.payment_receipt_no = payment_receipt_no;
    }

    public String getPaybill_number() {
        return paybill_number;
    }

    public void setPaybill_number(String paybill_number) {
        this.paybill_number = paybill_number;
    }

    public double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getInstitution_level() {
        return institution_level;
    }

    public void setInstitution_level(String institution_level) {
        this.institution_level = institution_level;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }

    public String getInstitution_number() {
        return institution_number;
    }

    public void setInstitution_number(String institution_number) {
        this.institution_number = institution_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCheckout_code() {
        return checkout_code;
    }

    public void setCheckout_code(String checkout_code) {
        this.checkout_code = checkout_code;
    }
}
