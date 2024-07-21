package com.enigma.controller;

import com.enigma.dto.request.ApproveTransactionLoanRequest;
import com.enigma.dto.request.LoanTypeRequest;
import com.enigma.dto.request.RejectTransactionLoanRequest;
import com.enigma.dto.request.TransactionLoanRequest;
import com.enigma.dto.response.CommonResponse;
import com.enigma.dto.response.GuaranteePictureResponse;
import com.enigma.dto.response.LoanTypeResponse;
import com.enigma.dto.response.TransactionLoanResponse;
import com.enigma.service.TransactionLoanService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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

    @PostMapping("/guarantee/{id}")
    public ResponseEntity<CommonResponse<GuaranteePictureResponse>> uploadGuaranteePicture(@RequestParam("file") MultipartFile multipartFile, @PathVariable String id){
        GuaranteePictureResponse guaranteePictureResponse= transactionLoanService.uploadGuaranteePicture(multipartFile, id);
        CommonResponse<GuaranteePictureResponse> guaranteePictureResponseCommonResponse=new CommonResponse<>();
        guaranteePictureResponseCommonResponse.setStatusCode(HttpStatus.CREATED.value());
        guaranteePictureResponseCommonResponse.setMessage("Succesfully Upload Guarantee Picture");
        guaranteePictureResponseCommonResponse.setData(Optional.of(guaranteePictureResponse));
        return ResponseEntity.ok(guaranteePictureResponseCommonResponse);
    }

    @PutMapping("/approve")
    public ResponseEntity<CommonResponse<TransactionLoanResponse>> approveTransaction(HttpServletRequest request, @RequestBody ApproveTransactionLoanRequest approveTransactionLoanRequest){
        String username= (String) request.getAttribute("username");
        TransactionLoanResponse transactionLoanResponse=transactionLoanService.approveLoanTransaction(username, approveTransactionLoanRequest);
        CommonResponse<TransactionLoanResponse> transactionLoanResponseCommonResponse=generateCommonResponse(HttpStatus.OK.value(), "Loan Request Approved Successfully", Optional.of(transactionLoanResponse));
        return ResponseEntity.ok(transactionLoanResponseCommonResponse);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<CommonResponse<TransactionLoanResponse>> payTransaction(@PathVariable String id){
        TransactionLoanResponse transactionLoanResponse=transactionLoanService.payLoanTransaction(id);
        CommonResponse<TransactionLoanResponse> transactionLoanResponseCommonResponse=generateCommonResponse(HttpStatus.OK.value(), "Transaction Paid Successfully", Optional.of(transactionLoanResponse));
        return ResponseEntity.ok(transactionLoanResponseCommonResponse);
    }

    @PutMapping("/reject")
    public ResponseEntity<CommonResponse<TransactionLoanResponse>> rejectTransaction(HttpServletRequest request, @RequestBody RejectTransactionLoanRequest rejectTransactionLoanRequest){
        String username= (String) request.getAttribute("username");
        TransactionLoanResponse transactionLoanResponse=transactionLoanService.rejectLoanTransaction(username, rejectTransactionLoanRequest);
        CommonResponse<TransactionLoanResponse> transactionLoanResponseCommonResponse=generateCommonResponse(HttpStatus.OK.value(), "Loan Request Rejected Successfully", Optional.of(transactionLoanResponse));
        return ResponseEntity.ok(transactionLoanResponseCommonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<TransactionLoanResponse>> findLoanTransactionById(@PathVariable String id){
        TransactionLoanResponse transactionLoanResponse=transactionLoanService.findLoanTransactionById(id);
        CommonResponse<TransactionLoanResponse> transactionLoanResponseCommonResponse=generateCommonResponse(HttpStatus.OK.value(), "Loan Transaction Found", Optional.of(transactionLoanResponse));
        return ResponseEntity.ok(transactionLoanResponseCommonResponse);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TransactionLoanResponse>>> viewAllLoanTransaction(){
        List<TransactionLoanResponse> transactionLoanResponseList=transactionLoanService.viewAllTransaction();
        CommonResponse<List<TransactionLoanResponse>> transactionLoanResponseCommonResponse=new CommonResponse<>();
        transactionLoanResponseCommonResponse.setMessage("Success Load All Loan Transaction Data");
        transactionLoanResponseCommonResponse.setStatusCode(HttpStatus.OK.value());
        transactionLoanResponseCommonResponse.setData(Optional.of(transactionLoanResponseList));
        return ResponseEntity.ok(transactionLoanResponseCommonResponse);
    }
}
