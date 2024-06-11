package com.example.tried.services;


import com.example.tried.dto.express.MpesaTransaction;

public interface MpesaService {

    int saveTransaction(MpesaTransaction mpesaTransaction);

    int updateTransaction(MpesaTransaction mpesaTransaction);
}
