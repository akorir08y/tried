package com.example.tried.services;


import com.example.tried.dto.express.MpesaTransaction;

public interface MpesaService {

    public int saveTransaction(MpesaTransaction mpesaTransaction);

    public int updateTransaction(MpesaTransaction mpesaTransaction);
}
