package com.enigma.service;

import com.enigma.dto.request.ApproveTransactionLoanRequest;
import com.enigma.dto.request.RejectTransactionLoanRequest;
import com.enigma.dto.request.TransactionLoanRequest;
import com.enigma.dto.response.GuaranteePictureResponse;
import com.enigma.dto.response.TransactionLoanResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransactionLoanService {
    public TransactionLoanResponse createTransaction(TransactionLoanRequest transactionLoanRequest);
    public TransactionLoanResponse findLoanTransactionById(String id);
    public List<TransactionLoanResponse> viewAllTransaction();
    public TransactionLoanResponse approveLoanTransaction(String username, ApproveTransactionLoanRequest approveTransactionLoanRequest);
    public TransactionLoanResponse rejectLoanTransaction(String username, RejectTransactionLoanRequest request);
    public TransactionLoanResponse payLoanTransaction(String id);
    public GuaranteePictureResponse uploadGuaranteePicture(MultipartFile file, String id);
}
