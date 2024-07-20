package com.enigma.controller;

import com.enigma.dto.request.InstalmentTypeRequest;
import com.enigma.dto.response.CommonResponse;
import com.enigma.dto.response.CustomerResponse;
import com.enigma.dto.response.InstalmentTypeResponse;
import com.enigma.service.InstalmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instalment")
public class InstalmentTypeController {
    private InstalmentTypeService instalmentTypeService;

    @Autowired
    public InstalmentTypeController(InstalmentTypeService instalmentTypeService) {
        this.instalmentTypeService = instalmentTypeService;
    }

    private CommonResponse<InstalmentTypeResponse> generateInstalmentTypeResponse(Integer code, String message, Optional<InstalmentTypeResponse> instalmentTypeResponse){
        CommonResponse<InstalmentTypeResponse> instalmentTypeResponseCommonResponse=new CommonResponse<>();
        instalmentTypeResponseCommonResponse.setStatusCode(code);
        instalmentTypeResponseCommonResponse.setMessage(message);
        instalmentTypeResponseCommonResponse.setData(instalmentTypeResponse);
        return instalmentTypeResponseCommonResponse;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> saveInstalmentType(@RequestBody InstalmentTypeRequest request){
        InstalmentTypeResponse instalmentTypeResponse=instalmentTypeService.createInstalmentType(request);
        CommonResponse<InstalmentTypeResponse> instalmentTypeResponseCommonResponse=generateInstalmentTypeResponse(HttpStatus.OK.value(), "Create Instalment Type Success", Optional.of(instalmentTypeResponse));
        return ResponseEntity.ok(instalmentTypeResponseCommonResponse);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<InstalmentTypeResponse>>> viewAllInstalmentType(){
        List<InstalmentTypeResponse> instalmentTypeResponseList=instalmentTypeService.viewAllInstalmentType();
        CommonResponse<List<InstalmentTypeResponse>> instalmentTypeResponseCommonResponse=new CommonResponse<>();
        instalmentTypeResponseCommonResponse.setStatusCode(HttpStatus.OK.value());
        instalmentTypeResponseCommonResponse.setMessage("Success Load Instalment Type Data");
        instalmentTypeResponseCommonResponse.setData(Optional.of(instalmentTypeResponseList));
        return ResponseEntity.ok(instalmentTypeResponseCommonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> getInstalmentTypeById(@PathVariable String id){
        InstalmentTypeResponse instalmentTypeResponse=instalmentTypeService.findInstalmentTypeById(id);
        CommonResponse<InstalmentTypeResponse> instalmentTypeResponseCommonResponse=generateInstalmentTypeResponse(HttpStatus.OK.value(), "Instalment Type Found", Optional.of(instalmentTypeResponse));
        return ResponseEntity.ok(instalmentTypeResponseCommonResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> updateInstalmentType(@PathVariable String id, @RequestBody InstalmentTypeRequest request){
        InstalmentTypeResponse instalmentTypeResponse=instalmentTypeService.updateInstalmentTypePut(request,id);
        CommonResponse<InstalmentTypeResponse> instalmentTypeResponseCommonResponse=generateInstalmentTypeResponse(HttpStatus.OK.value(), "Update Instalment Type Success", Optional.of(instalmentTypeResponse));
        return ResponseEntity.ok(instalmentTypeResponseCommonResponse);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<CommonResponse<?>> deleteInstalmentType(@PathVariable String id){
        instalmentTypeService.deleteInstalmentType(id);
        CommonResponse<?> commonResponse=generateInstalmentTypeResponse(HttpStatus.OK.value(), "Delete Instalment Type Success", Optional.empty());
        return ResponseEntity.ok(commonResponse);
    }
}
