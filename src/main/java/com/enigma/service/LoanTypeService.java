package com.enigma.service;

import com.enigma.dto.request.LoanTypeRequest;
import com.enigma.dto.response.LoanTypeResponse;

import java.util.List;

public interface LoanTypeService {
    public LoanTypeResponse createLoanType(LoanTypeRequest request);
    public LoanTypeResponse findLoanTypeById(String id);
    public List<LoanTypeResponse> viewAllLoanType();
    public LoanTypeResponse updateLoanTypePut(LoanTypeRequest request, String id);
    public LoanTypeResponse updateLoanTypePatch(LoanTypeRequest request, String id);
    public void deleteLoanType(String id);
}
