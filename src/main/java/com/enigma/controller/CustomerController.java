package com.enigma.controller;

import com.enigma.dto.request.CustomerRequest;
import com.enigma.dto.response.CommonResponse;
import com.enigma.dto.response.CustomerResponse;
import com.enigma.dto.response.LoanDocumentResponse;
import com.enigma.dto.response.PhotoProfileResponse;
import com.enigma.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private CommonResponse<CustomerResponse> generateCustomerResponse(Integer code, String message, Optional<CustomerResponse> customerResponse){
        CommonResponse<CustomerResponse> customerResponseCommonResponse=new CommonResponse<>();
        customerResponseCommonResponse.setStatusCode(code);
        customerResponseCommonResponse.setMessage(message);
        customerResponseCommonResponse.setData(customerResponse);
        return customerResponseCommonResponse;
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getCustomers(){
        List<CustomerResponse> customerResponseList= customerService.viewCustomers();
        CommonResponse<List<CustomerResponse>> commonResponse=new CommonResponse<>();
        commonResponse.setStatusCode(HttpStatus.OK.value());
        commonResponse.setMessage("Success Load All Customer Data");
        commonResponse.setData(Optional.of(customerResponseList));
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> getCustomerById(@PathVariable String id){
        CustomerResponse customerFound = customerService.findCustomerById(id);
        CommonResponse<CustomerResponse> customerResponseCommonResponse=generateCustomerResponse(HttpStatus.OK.value(), "Customer Found", Optional.of(customerFound));
        return ResponseEntity.ok(customerResponseCommonResponse);
    }

    @PostMapping("/avatar/{id}")
    public ResponseEntity<CommonResponse<PhotoProfileResponse>> uploadImage(@RequestParam("file") MultipartFile multipartFile, @PathVariable String id){
        PhotoProfileResponse photoProfileResponse=customerService.uploadProfile(multipartFile, id);
        CommonResponse<PhotoProfileResponse> photoProfileResponseCommonResponse=new CommonResponse<>();
        photoProfileResponseCommonResponse.setMessage("Succesfully Upload Photo Profile");
        photoProfileResponseCommonResponse.setStatusCode(HttpStatus.CREATED.value());
        photoProfileResponseCommonResponse.setData(Optional.of(photoProfileResponse));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(photoProfileResponseCommonResponse);
    }

    @PostMapping("/document/{id}")
    public ResponseEntity<CommonResponse<LoanDocumentResponse>> uploadDocument(@RequestParam("file") MultipartFile multipartFile, @PathVariable String id){
        LoanDocumentResponse loanDocumentResponse=customerService.uploadLoanDocument(multipartFile,id);
        CommonResponse<LoanDocumentResponse> loanDocumentResponseCommonResponse=new CommonResponse<>();
        loanDocumentResponseCommonResponse.setMessage("Succesfully Upload Loan Document");
        loanDocumentResponseCommonResponse.setStatusCode(HttpStatus.CREATED.value());
        loanDocumentResponseCommonResponse.setData(Optional.of(loanDocumentResponse));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loanDocumentResponseCommonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> removeCustomer(@PathVariable String id){
        CustomerResponse customerResponseDelete=customerService.deleteCustomer(id);
        CommonResponse<CustomerResponse> customerResponseCommonResponse=generateCustomerResponse(HttpStatus.OK.value(), "Customer Deleted Succesfully", Optional.of(customerResponseDelete));
        return ResponseEntity.ok(customerResponseCommonResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> updateCustomerPatch(@PathVariable String id, @RequestBody CustomerRequest request){
        CustomerResponse customerResponse=customerService.updateCustomerPatch(request,id);
        CommonResponse<CustomerResponse> customerResponseCommonResponse=generateCustomerResponse(HttpStatus.OK.value(), "Customer Updated Succesfully", Optional.of(customerResponse));
        return ResponseEntity.ok(customerResponseCommonResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> updateCustomerPut(@PathVariable String id, @RequestBody CustomerRequest request){
        CustomerResponse customerResponse=customerService.updateCustomerPut(request,id);
        CommonResponse<CustomerResponse> customerResponseCommonResponse=generateCustomerResponse(HttpStatus.OK.value(), "Customer Updated Succesfully", Optional.of(customerResponse));
        return ResponseEntity.ok(customerResponseCommonResponse);
    }

}
