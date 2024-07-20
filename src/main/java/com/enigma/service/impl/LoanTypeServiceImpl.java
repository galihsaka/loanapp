package com.enigma.service.impl;

import com.enigma.dto.request.LoanTypeRequest;
import com.enigma.dto.response.LoanTypeResponse;
import com.enigma.entity.InstalmentType;
import com.enigma.entity.LoanType;
import com.enigma.repository.LoanTypeRepository;
import com.enigma.service.LoanTypeService;
import com.enigma.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanTypeServiceImpl implements LoanTypeService {
    private LoanTypeRepository loanTypeRepository;

    @Autowired
    public LoanTypeServiceImpl(LoanTypeRepository loanTypeRepository) {
        this.loanTypeRepository = loanTypeRepository;
    }

    private LoanTypeResponse generateLoanTypeResponse(LoanType loanType){
        LoanTypeResponse loanTypeResponse=new LoanTypeResponse();
        loanTypeResponse.setId(loanType.getId());
        loanTypeResponse.setType(loanType.getType());
        loanTypeResponse.setMaxLoan(loanType.getMaxLoan());
        return loanTypeResponse;
    }

    private boolean checkLoanTypeIfExist(String loanType){
        Optional<LoanType> loanTypeOptional=loanTypeRepository.findByType(loanType);
        LoanType loanType1=loanTypeOptional.orElse(null);
        if(loanType1==null){
            return false;
        }
        else return true;
    }

    private LoanType findLoanTypeByIdOrThrowNotFound(String id) {
        return loanTypeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Loan Type Not Found"));
    }

    @Override
    public LoanTypeResponse createLoanType(LoanTypeRequest request) {
        LoanTypeResponse loanTypeResponse;
        LoanType loanType=new LoanType();
        if(!checkLoanTypeIfExist(request.getType())){
            loanType.setMaxLoan(request.getMaxLoan());
            loanType.setType(request.getType());
            loanType=loanTypeRepository.save(loanType);
            loanTypeResponse=generateLoanTypeResponse(loanType);
        }
        else{
            Optional<LoanType> loanTypeOptional=loanTypeRepository.findByType(request.getType());
            loanType=loanTypeOptional.orElse(null);
            loanTypeResponse=generateLoanTypeResponse(loanType);
        }
        return loanTypeResponse;
    }

    @Override
    public LoanTypeResponse findLoanTypeById(String id) {
        LoanType loanType=findLoanTypeByIdOrThrowNotFound(id);
        return generateLoanTypeResponse(loanType);
    }

    @Override
    public List<LoanTypeResponse> viewAllLoanType() {
        List<LoanType> loanTypeList=loanTypeRepository.findAll();
        List<LoanTypeResponse> loanTypeResponses=new ArrayList<>();
        for(int i=0; i<loanTypeList.size();i++){
            loanTypeResponses.add(generateLoanTypeResponse(loanTypeList.get(i)));
        }
        return loanTypeResponses;
    }

    @Override
    public LoanTypeResponse updateLoanTypePut(LoanTypeRequest request, String id) {
        LoanType loanTypeFound=findLoanTypeByIdOrThrowNotFound(id);
        loanTypeFound.setType(request.getType());
        loanTypeFound.setMaxLoan(request.getMaxLoan());
        loanTypeFound=loanTypeRepository.save(loanTypeFound);
        return generateLoanTypeResponse(loanTypeFound);
    }

    @Override
    public LoanTypeResponse updateLoanTypePatch(LoanTypeRequest request, String id) {
        LoanType loanTypeFound=findLoanTypeByIdOrThrowNotFound(id);
        if(request.getType()!=null){
            loanTypeFound.setType(request.getType());
        }
        if(request.getMaxLoan()!=null){
            loanTypeFound.setMaxLoan(request.getMaxLoan());
        }
        loanTypeFound=loanTypeRepository.save(loanTypeFound);
        return generateLoanTypeResponse(loanTypeFound);
    }

    @Override
    public void deleteLoanType(String id) {
        LoanType loanTypeFound=findLoanTypeByIdOrThrowNotFound(id);
        loanTypeRepository.delete(loanTypeFound);
    }
}
