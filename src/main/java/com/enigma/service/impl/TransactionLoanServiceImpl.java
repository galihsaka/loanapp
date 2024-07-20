package com.enigma.service.impl;

import com.enigma.dto.request.TransactionLoanRequest;
import com.enigma.dto.response.TransactionLoanDetailsResponse;
import com.enigma.dto.response.TransactionLoanResponse;
import com.enigma.entity.*;
import com.enigma.repository.*;
import com.enigma.service.TransactionLoanService;
import com.enigma.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionLoanServiceImpl implements TransactionLoanService {
    private CustomerRepository customerRepository;
    private LoanTypeRepository loanTypeRepository;
    private InstalmentTypeRepository instalmentTypeRepository;
    private TransactionLoanRepository transactionLoanRepository;
    private TransactionLoanDetailRepository transactionLoanDetailRepository;

    @Autowired
    public TransactionLoanServiceImpl(CustomerRepository customerRepository, TransactionLoanRepository transactionLoanRepository, TransactionLoanDetailRepository transactionLoanDetailRepository, InstalmentTypeRepository instalmentTypeRepository, LoanTypeRepository loanTypeRepository) {
        this.customerRepository = customerRepository;
        this.transactionLoanRepository = transactionLoanRepository;
        this.transactionLoanDetailRepository = transactionLoanDetailRepository;
        this.instalmentTypeRepository = instalmentTypeRepository;
        this.loanTypeRepository = loanTypeRepository;
    }

    private TransactionLoanDetailsResponse generateTransactionLoanDetailResponse(TransactionLoanDetails transactionLoanDetails){
        TransactionLoanDetailsResponse transactionLoanDetailsResponse=new TransactionLoanDetailsResponse();
        transactionLoanDetailsResponse.setId(transactionLoanDetails.getId());
        transactionLoanDetailsResponse.setLoanStatus(transactionLoanDetails.getLoanStatus());
        transactionLoanDetailsResponse.setTransactionDate(transactionLoanDetails.getTransactionDate());
        transactionLoanDetailsResponse.setNominal(transactionLoanDetails.getNominal());
        transactionLoanDetailsResponse.setCreatedAt(transactionLoanDetails.getCreatedAt());
        transactionLoanDetailsResponse.setUpdatedAt(transactionLoanDetails.getUpdatedAt());
        transactionLoanDetailsResponse.setGuaranteePicture(transactionLoanDetails.getGuaranteePicture());
        return transactionLoanDetailsResponse;
    }

    private TransactionLoanResponse generateTransactionLoanResponse(TransactionLoan transactionLoan){
        TransactionLoanResponse transactionLoanResponse=new TransactionLoanResponse();
        transactionLoanResponse.setId(transactionLoan.getId());
        transactionLoanResponse.setLoanTypeId(transactionLoan.getLoanType().getId());
        transactionLoanResponse.setCreatedAt(transactionLoan.getCreatedAt());
        transactionLoanResponse.setTransactionLoanDetailsResponse(generateTransactionLoanDetailResponse(transactionLoan.getTransactionLoanDetails()));
        transactionLoanResponse.setNominal(transactionLoan.getNominal());
        transactionLoanResponse.setApprovalStatus(transactionLoan.getApprovalStatus());
        transactionLoanResponse.setApprovedAt(transactionLoan.getApprovedAt());
        transactionLoanResponse.setApprovedBy(transactionLoan.getApprovedBy());
        transactionLoanResponse.setCustomerId(transactionLoan.getCustomer().getId());
        transactionLoanResponse.setInstalmentTypeId(transactionLoan.getInstalmentType().getId());
        transactionLoanResponse.setRejectedAt(transactionLoan.getRejectedAt());
        transactionLoanResponse.setRejectedBy(transactionLoan.getRejectedBy());
        transactionLoanResponse.setUpdatedAt(transactionLoan.getUpdatedAt());
        return transactionLoanResponse;
    }

    public TransactionLoanResponse createTransaction(TransactionLoanRequest transactionLoanRequest){
        Optional<LoanType> loanTypeOptional=loanTypeRepository.findById(transactionLoanRequest.getLoanTypeId());
        LoanType loanType = loanTypeOptional.orElseThrow(()->new ResourceNotFoundException("Loan Type Not Found"));
        Optional<InstalmentType> instalmentTypeOptional=instalmentTypeRepository.findById(transactionLoanRequest.getInstalmentTypeId());
        InstalmentType instalmentType=instalmentTypeOptional.orElseThrow(()->new ResourceNotFoundException("Instalment Type Not Found"));
        Optional<Customer> customerOptional=customerRepository.findById(transactionLoanRequest.getCustomerId());
        Customer customer=customerOptional.orElseThrow(()->new ResourceNotFoundException("Customer Not Found"));
        TransactionLoanDetails transactionLoanDetails=new TransactionLoanDetails();
        transactionLoanDetails.setGuaranteePicture(null);
        transactionLoanDetails.setTransactionDate(new Date());
        transactionLoanDetails.setLoanStatus(null);
        transactionLoanDetails.setCreatedAt(null);
        transactionLoanDetails.setUpdatedAt(null);
        transactionLoanDetails.setNominal(null);
        transactionLoanDetails=transactionLoanDetailRepository.save(transactionLoanDetails);
        TransactionLoan transactionLoan=new TransactionLoan();
        transactionLoan.setTransactionLoanDetails(transactionLoanDetails);
        transactionLoan.setLoanType(loanType);
        transactionLoan.setInstalmentType(instalmentType);
        transactionLoan.setCustomer(customer);
        transactionLoan.setNominal(transactionLoanRequest.getNominal());
        transactionLoan.setApprovedAt(null);
        transactionLoan.setApprovedBy(null);
        transactionLoan.setApprovalStatus(null);
        transactionLoan.setCreatedAt(new Date());
        transactionLoan.setUpdatedAt(null);
        transactionLoan.setRejectedAt(null);
        transactionLoan.setRejectedBy(null);
        transactionLoan=transactionLoanRepository.save(transactionLoan);
        return generateTransactionLoanResponse(transactionLoan);
    }
}
