package com.example.tried.utils;

import okhttp3.MediaType;

// Constants to be Used in the Daraja API
public class Constants {

    public static final String BASIC_AUTH_STRING = "Basic";
    public static final String AUTHORIZATION_HEADER_STRING = "Authorization";
    public static final String BEARER_AUTH_STRING = "Bearer";
    public static final String CACHE_CONTROL_HEADER = "cache-control";
    public static final String CACHE_CONTROL_HEADER_VALUE = "no-cache";
    public static MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static final String TRANSACTION_STATUS_QUERY_COMMAND = "TransactionStatusQuery";
    public static final String ACCOUNT_BALANCE_COMMAND = "AccountBalance";
    public static final String TRANSACTION_STATUS_VALUE = "Transaction Status";

    public static final String COMMAND_ID = "SalaryPayment";

    public static final String MSISDN_IDENTIFIER = "1";
    public static final String TILL_NUMBER_IDENTIFIER = "2";
    public static final int SHORT_CODE_IDENTIFIER = 4;

    public static final int SHORT_CODE = 600986;

    public static final String REVERSAL_COMMAND = "TransactionReversal";

    public static final int RECIEVER_IDENTIFIER_TYPE = 11;

    public static final int AMOUNT_TO_BE_PAID = 100;

    public static final String PHONE_NUMBER = "0708374149";

    public static final String LAKEATTS_PAYBILL = "4113681";

    public static final String ACCOUNT_BALANCE_REMARKS = "Check Account Balance";

    public static final String CUSTOMER_PAYBILL_ONLINE = "CustomerPayBillOnline";

    public static final String TAX_COMMAND_KRA = "PayTaxToKRA";

    public static final String KRA_SENDER_IDENTIFIER = "4";

    public static final String KRA_RECIEVER_IDENTIFIER = "4";

    public static final String KRA_PAYBILL = "572572";

    public static final String CUSTOMER_PAYBILL = "888880";

    public static final String ACCOUNT_REFERENCE = "353353";

    public static final String REMARKS = "Simple MPESA Transaction";

    public static final String TRX_CODE = "BG";

    public static final String MERCHANT_NAME = "TEST SUPERMARKET";

    public static final String REF_NO = "Invoice Test";

    public static final String QR_CODE_SIZE = "300";
}
