package com.example.tried.dto.express;

import lombok.Data;

@Data
public class MpesaTransaction {

    private Integer invoice_id;
    private String payment_receipt_no;
    private String paybill_number;
    private double payment_amount;
    private String transaction_date;
    private String institution_level;
    private String institution_name;
    private String institution_number;
    private String phone_number;
    private String checkout_code;
}
