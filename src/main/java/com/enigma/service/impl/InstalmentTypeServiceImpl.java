package com.enigma.service.impl;

import com.enigma.dto.request.InstalmentTypeRequest;
import com.enigma.dto.response.InstalmentTypeResponse;
import com.enigma.entity.InstalmentType;
import com.enigma.repository.InstalmentTypeRepository;
import com.enigma.service.InstalmentTypeService;
import com.enigma.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    private InstalmentTypeRepository instalmentTypeRepository;

    @Autowired
    public InstalmentTypeServiceImpl(InstalmentTypeRepository instalmentTypeRepository) {
        this.instalmentTypeRepository = instalmentTypeRepository;
    }

    private InstalmentTypeResponse generateInstalmentTypeResponse(InstalmentType instalmentType){
        InstalmentTypeResponse instalmentTypeResponse=new InstalmentTypeResponse();
        instalmentTypeResponse.setId(instalmentType.getId());
        instalmentTypeResponse.setInstalmentType(instalmentType.getInstalmentType());
        return instalmentTypeResponse;
    }

    private boolean checkInstalmentTypeIfExist(InstalmentType.EInstalmentType instalmentType){
        Optional<InstalmentType> instalmentTypeFound =instalmentTypeRepository.findByInstalmentType(instalmentType);
        InstalmentType instalmentTypeEntity=instalmentTypeFound.orElse(null);
        if(instalmentTypeEntity==null){
            return false;
        }
        else {
            return true;
        }
    }

    private InstalmentType findInstalmentTypeByIdOrThrowNotFound(String id) {
        return instalmentTypeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Instalment Type Not Found"));
    }

    public InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest request){
        InstalmentTypeResponse instalmentTypeResponse;
        InstalmentType instalmentType =new InstalmentType();
        if(!checkInstalmentTypeIfExist(request.getInstalmentType())){
            instalmentType.setInstalmentType(request.getInstalmentType());
            instalmentType =instalmentTypeRepository.save(instalmentType);
            instalmentTypeResponse=generateInstalmentTypeResponse(instalmentType);
            instalmentTypeResponse.setInstalmentTypeStatus("New Instalment Type Added");
        }
        else{
            Optional<InstalmentType> instalmentTypeFound =instalmentTypeRepository.findByInstalmentType(request.getInstalmentType());
            InstalmentType instalmentTypeEntity=instalmentTypeFound.orElse(null);
            instalmentTypeResponse=generateInstalmentTypeResponse(instalmentTypeEntity);
            instalmentTypeResponse.setInstalmentTypeStatus("No New Instalment Type Added, Because Its Already Exist");
        }
        return instalmentTypeResponse;
    }

    @Override
    public List<InstalmentTypeResponse> viewAllInstalmentType() {
        List<InstalmentTypeResponse> instalmentTypeResponseList=new ArrayList<>();
        List<InstalmentType> instalmentTypeList=instalmentTypeRepository.findAll();
        for(int i=0; i<instalmentTypeList.size();i++){
            InstalmentTypeResponse instalmentTypeResponse=generateInstalmentTypeResponse(instalmentTypeList.get(i));
            instalmentTypeResponse.setInstalmentTypeStatus("Instalment type sequence number: "+i);
            instalmentTypeResponseList.add(instalmentTypeResponse);
        }
        return instalmentTypeResponseList;
    }

    @Override
    public InstalmentTypeResponse findInstalmentTypeById(String id) {
        InstalmentType instalmentType=findInstalmentTypeByIdOrThrowNotFound(id);
        InstalmentTypeResponse instalmentTypeResponse=generateInstalmentTypeResponse(instalmentType);
        instalmentTypeResponse.setInstalmentTypeStatus("Data Found Successfully");
        return instalmentTypeResponse;
    }

    @Override
    public InstalmentTypeResponse updateInstalmentTypePut(InstalmentTypeRequest request, String id) {
        InstalmentType instalmentType=findInstalmentTypeByIdOrThrowNotFound(id);
        instalmentType.setInstalmentType(request.getInstalmentType());
        instalmentType=instalmentTypeRepository.save(instalmentType);
        InstalmentTypeResponse instalmentTypeResponse=generateInstalmentTypeResponse(instalmentType);
        instalmentTypeResponse.setInstalmentTypeStatus("Updated Successfully");
        return instalmentTypeResponse;
    }

    @Override
    public void deleteInstalmentType(String id) {
        InstalmentType instalmentType=findInstalmentTypeByIdOrThrowNotFound(id);
        instalmentTypeRepository.delete(instalmentType);
    }
}
