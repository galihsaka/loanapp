package com.enigma.service.impl;

import com.enigma.dto.request.CustomerRequest;
import com.enigma.dto.response.CustomerResponse;
import com.enigma.dto.response.PhotoProfileResponse;
import com.enigma.entity.Customer;
import com.enigma.entity.ProfilePicture;
import com.enigma.repository.CustomerRepository;
import com.enigma.repository.ProfilePictureRepository;
import com.enigma.service.CustomerService;
import com.enigma.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ProfilePictureRepository profilePictureRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ProfilePictureRepository profilePictureRepository) {
        this.customerRepository = customerRepository;
        this.profilePictureRepository=profilePictureRepository;
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
        return customerResponse;
    }

    @Override
    public void deleteCustomer(String id) {
        Customer customer=findCustomerByIdOrThrowNotFound(id);
        customerRepository.deleteById(id);
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
    public PhotoProfileResponse uploadProfile(MultipartFile file, String id){
        String oriFileName= file.getOriginalFilename();
        String fileName=id+"_"+oriFileName;
        String contentType=file.getContentType();
        Long size=file.getSize();
        ProfilePicture profilePicture=new ProfilePicture();
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
        Customer customer=findCustomerByIdOrThrowNotFound(id);
        customer.setProfilePicture(profilePicture);
        customerRepository.save(customer);
        return photoProfileResponse;
    }
}
