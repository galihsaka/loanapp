package com.enigma.service;

import com.enigma.dto.request.TransactionLoanRequest;
import com.enigma.dto.response.TransactionLoanResponse;

public interface TransactionLoanService {
    public TransactionLoanResponse createTransaction(TransactionLoanRequest transactionLoanRequest);
}
