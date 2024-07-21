package com.enigma.service.impl;

import com.enigma.dto.request.CustomerRequest;
import com.enigma.dto.response.CustomerResponse;
import com.enigma.dto.response.LoanDocumentResponse;
import com.enigma.dto.response.PhotoProfileResponse;
import com.enigma.entity.Customer;
import com.enigma.entity.LoanDocument;
import com.enigma.entity.ProfilePicture;
import com.enigma.repository.CustomerRepository;
import com.enigma.repository.LoanDocumentRepository;
import com.enigma.repository.ProfilePictureRepository;
import com.enigma.service.CustomerService;
import com.enigma.utils.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ProfilePictureRepository profilePictureRepository;
    private LoanDocumentRepository loanDocumentRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ProfilePictureRepository profilePictureRepository,LoanDocumentRepository loanDocumentRepository) {
        this.customerRepository = customerRepository;
        this.profilePictureRepository=profilePictureRepository;
        this.loanDocumentRepository=loanDocumentRepository;
    }

    private Customer findCustomerByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer Not Found"));
    }

    private CustomerResponse convertToCustomerResponse(Customer customer){
        CustomerResponse customerResponse=new CustomerResponse();
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setDateOfBirth(customer.getDateOfBirth());
        customerResponse.setId(customer.getId());
        customerResponse.setPhone(customer.getPhone());
        customerResponse.setStatus(customer.getStatus());
        customerResponse.setDeleted(customer.isDeleted());
        return customerResponse;
    }

    @Override
    public CustomerResponse deleteCustomer(String id) {
        Customer customer=findCustomerByIdOrThrowNotFound(id);
        customer.setDeleted(Boolean.TRUE);
        customer=customerRepository.save(customer);
        return convertToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse findCustomerById(String id) {
        Customer customer=findCustomerByIdOrThrowNotFound(id);
        return convertToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomerPut(CustomerRequest request, String id) {
        Customer customerFound=findCustomerByIdOrThrowNotFound(id);
        customerFound.setFirstName(request.getFirstName());
        customerFound.setLastName(request.getLastName());
        customerFound.setPhone(request.getPhone());
        customerFound.setDateOfBirth(request.getDateOfBirth());
        customerFound.setStatus(request.getStatus());
        customerFound=customerRepository.save(customerFound);
        return convertToCustomerResponse(customerFound);
    }

    @Override
    public CustomerResponse updateCustomerPatch(CustomerRequest request, String id) {
        Customer customerFound=findCustomerByIdOrThrowNotFound(id);
        if(request.getFirstName()!=null){
            customerFound.setFirstName(request.getFirstName());
        }
        if(request.getLastName()!=null){
            customerFound.setLastName(request.getLastName());
        }
        if(request.getPhone()!=null){
            customerFound.setPhone(request.getPhone());
        }
        if(request.getDateOfBirth()!=null){
            customerFound.setDateOfBirth(request.getDateOfBirth());
        }
        if(request.getStatus()!=null){
            customerFound.setStatus(request.getStatus());
        }
        customerFound=customerRepository.save(customerFound);
        return convertToCustomerResponse(customerFound);
    }

    @Override
    public List<CustomerResponse> viewCustomers() {
        List<Customer> customers=customerRepository.findAll();
        List<CustomerResponse> customerResponses =new ArrayList<>();
        for(int i=0; i<customers.size();i++){
            customerResponses.add(convertToCustomerResponse(customers.get(i)));
        }
        return customerResponses;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public PhotoProfileResponse uploadProfile(MultipartFile file, String id){
        Customer customerFound=findCustomerByIdOrThrowNotFound(id);
        ProfilePicture profilePicture;
        String oriFileName= file.getOriginalFilename();
        String fileName=id+"_"+oriFileName;
        String contentType=file.getContentType();
        Long size=file.getSize();
        Optional<ProfilePicture> profilePictureOptional=profilePictureRepository.findByName(fileName);
        ProfilePicture profilePicture1=profilePictureOptional.orElse(null);
        if(profilePicture1==null){
            profilePicture=new ProfilePicture();
        }else{
            profilePicture=profilePicture1;
        }
        profilePicture.setName(fileName);
        profilePicture.setSize(size);
        profilePicture.setContentType(contentType);
        try{
            Path fileStorageLocation=Path.of("assets/photo_profile/");
            Path targetLocation=fileStorageLocation.resolve(fileName);
            profilePicture.setUrl(targetLocation.toString());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        profilePicture=profilePictureRepository.save(profilePicture);
        PhotoProfileResponse photoProfileResponse=new PhotoProfileResponse();
        photoProfileResponse.setName(profilePicture.getName());
        photoProfileResponse.setSize(profilePicture.getSize());
        photoProfileResponse.setContentType(profilePicture.getContentType());
        photoProfileResponse.setUrl(profilePicture.getUrl());
        customerFound.setProfilePicture(profilePicture);
        customerRepository.save(customerFound);
        return photoProfileResponse;
    }

    public LoanDocumentResponse uploadLoanDocument(MultipartFile file, String id){
        Customer customerFound=findCustomerByIdOrThrowNotFound(id);
        LoanDocument loanDocument;
        String oriFileName= file.getOriginalFilename();
        String fileName=id+"_"+oriFileName;
        String contentType=file.getContentType();
        Long size=file.getSize();
        Optional<LoanDocument> loanDocumentOptional=loanDocumentRepository.findByName(fileName);
        LoanDocument loanDocument1=loanDocumentOptional.orElse(null);
        if(loanDocument1==null){
            loanDocument=new LoanDocument();
        }else{
            loanDocument=loanDocument1;
        }
        loanDocument.setName(fileName);
        loanDocument.setSize(size);
        loanDocument.setContentType(contentType);
        loanDocument.setCustomer(customerFound);
        try{
            Path fileStorageLocation=Path.of("assets/loan_document/");
            Path targetLocation=fileStorageLocation.resolve(fileName);
            String path="assets/loan_document/"+fileName;
            loanDocument.setPath(path);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        loanDocument=loanDocumentRepository.save(loanDocument);
        LoanDocumentResponse loanDocumentResponse=new LoanDocumentResponse();
        loanDocumentResponse.setId(loanDocument.getId());
        loanDocumentResponse.setPath(loanDocument.getPath());
        loanDocumentResponse.setCustomerId(loanDocument.getCustomer().getId());
        loanDocumentResponse.setName(loanDocument.getName());
        loanDocumentResponse.setContentType(loanDocument.getContentType());
        loanDocumentResponse.setSize(loanDocument.getSize());
        return loanDocumentResponse;
    }
}
