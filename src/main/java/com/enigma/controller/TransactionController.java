package com.enigma.controller;

import com.enigma.dto.request.LoanTypeRequest;
import com.enigma.dto.request.TransactionLoanRequest;
import com.enigma.dto.response.CommonResponse;
import com.enigma.dto.response.LoanTypeResponse;
import com.enigma.dto.response.TransactionLoanResponse;
import com.enigma.service.TransactionLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private TransactionLoanService transactionLoanService;
    @Autowired
    public TransactionController(TransactionLoanService transactionLoanService) {
        this.transactionLoanService = transactionLoanService;
    }
    private CommonResponse<TransactionLoanResponse> generateCommonResponse(Integer code, String message, Optional<TransactionLoanResponse> transactionLoanResponseOptional){
        CommonResponse<TransactionLoanResponse> transactionLoanResponseCommonResponse=new CommonResponse<>();
        transactionLoanResponseCommonResponse.setStatusCode(code);
        transactionLoanResponseCommonResponse.setMessage(message);
        transactionLoanResponseCommonResponse.setData(transactionLoanResponseOptional);
        return transactionLoanResponseCommonResponse;
    }
    @PostMapping
    public ResponseEntity<CommonResponse<TransactionLoanResponse>> saveTransaction(@RequestBody TransactionLoanRequest request){
        TransactionLoanResponse transactionLoanResponse=transactionLoanService.createTransaction(request);
        CommonResponse<TransactionLoanResponse> transactionLoanResponseCommonResponse=generateCommonResponse(HttpStatus.OK.value(), "Loan Request Added Successfully", Optional.of(transactionLoanResponse));
        return ResponseEntity.ok(transactionLoanResponseCommonResponse);
    }
}
