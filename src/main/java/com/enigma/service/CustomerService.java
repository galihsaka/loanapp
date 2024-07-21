package com.enigma.service;

import com.enigma.dto.request.CustomerRequest;
import com.enigma.dto.response.CustomerResponse;
import com.enigma.dto.response.LoanDocumentResponse;
import com.enigma.dto.response.PhotoProfileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    public CustomerResponse deleteCustomer(String id);
    public CustomerResponse findCustomerById(String id);
    public CustomerResponse updateCustomerPut(CustomerRequest request, String id);
    public CustomerResponse updateCustomerPatch(CustomerRequest request, String id);
    public List<CustomerResponse> viewCustomers();
    public PhotoProfileResponse uploadProfile(MultipartFile file, String id);
    public LoanDocumentResponse uploadLoanDocument(MultipartFile file, String id);
}
