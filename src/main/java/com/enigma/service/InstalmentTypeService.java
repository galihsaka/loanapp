package com.enigma.service;

import com.enigma.dto.request.InstalmentTypeRequest;
import com.enigma.dto.response.InstalmentTypeResponse;

import java.util.List;

public interface InstalmentTypeService {
    public InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest request);
    public List<InstalmentTypeResponse> viewAllInstalmentType();
    public InstalmentTypeResponse findInstalmentTypeById(String id);
    public InstalmentTypeResponse updateInstalmentTypePut(InstalmentTypeRequest request, String id);
    public void deleteInstalmentType(String id);
}
