package com.example.tried.services;

import com.example.tried.dto.express.MpesaTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class MpesaServiceImpl implements MpesaService{

    @Autowired
    JdbcTemplate jdbcTemplateTwo;

    @Override
    public int saveTransaction(MpesaTransaction mpesaTransaction) {
        String query = "insert into mobile_payment (institution_number, institution_name, institution_level, invoice_id, payment_receipt_no, " +
                "paybill_number, payment_amount, transaction_date, phone_number,check_out_code) " +
                "values(?,?,?,?,?,?,?,?,?,?)";

        return jdbcTemplateTwo.update(query, mpesaTransaction.getInstitution_number(),
                mpesaTransaction.getInstitution_name(),mpesaTransaction.getInstitution_level(), mpesaTransaction.getInvoice_id(),
                mpesaTransaction.getPayment_receipt_no(), mpesaTransaction.getPaybill_number(), mpesaTransaction.getPayment_amount(),
                mpesaTransaction.getTransaction_date(),mpesaTransaction.getPhone_number());
    }

    @Override
    public int updateTransaction(MpesaTransaction mpesaTransaction) {
        String query = "update mobile_payment set transaction_date=?,payment_receipt_no=? where check_out_code=?";
        return jdbcTemplateTwo.update(query, mpesaTransaction.getTransaction_date(), mpesaTransaction.getPayment_receipt_no());
    }


}
