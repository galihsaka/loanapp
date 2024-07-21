package com.enigma.controller;

import com.enigma.dto.request.LoanTypeRequest;
import com.enigma.dto.response.CommonResponse;
import com.enigma.dto.response.LoanTypeResponse;
import com.enigma.service.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loantype")
public class LoanTypeController {
    private LoanTypeService loanTypeService;

    @Autowired
    public LoanTypeController(LoanTypeService loanTypeService) {
        this.loanTypeService = loanTypeService;
    }

    private CommonResponse<LoanTypeResponse> generateCommonResponse(Integer code, String message, Optional<LoanTypeResponse> loanTypeResponse){
        CommonResponse<LoanTypeResponse> loanTypeResponseCommonResponse=new CommonResponse<>();
        loanTypeResponseCommonResponse.setStatusCode(code);
        loanTypeResponseCommonResponse.setMessage(message);
        loanTypeResponseCommonResponse.setData(loanTypeResponse);
        return loanTypeResponseCommonResponse;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<LoanTypeResponse>> saveLoanType(@RequestBody LoanTypeRequest loanTypeRequest){
        LoanTypeResponse loanTypeResponse=loanTypeService.createLoanType(loanTypeRequest);
        CommonResponse<LoanTypeResponse> loanTypeResponseCommonResponse=generateCommonResponse(HttpStatus.OK.value(), "Loan Type Added Successfully", Optional.of(loanTypeResponse));
        return ResponseEntity.ok(loanTypeResponseCommonResponse);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<List<LoanTypeResponse>>> viewAllLoanType(){
        List<LoanTypeResponse> loanTypeResponseList=loanTypeService.viewAllLoanType();
        CommonResponse<List<LoanTypeResponse>> commonResponses=new CommonResponse<>();
        commonResponses.setStatusCode(HttpStatus.OK.value());
        commonResponses.setMessage("Success Load Loan Type Data");
        commonResponses.setData(Optional.of(loanTypeResponseList));
        return ResponseEntity.ok(commonResponses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<LoanTypeResponse>> findLoanTypeById(@PathVariable String id){
        LoanTypeResponse loanTypeResponse=loanTypeService.findLoanTypeById(id);
        CommonResponse<LoanTypeResponse> loanTypeResponseCommonResponse=generateCommonResponse(HttpStatus.OK.value(), "Loan Type Found", Optional.of(loanTypeResponse));
        return ResponseEntity.ok(loanTypeResponseCommonResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<LoanTypeResponse>> updateLoanTypePut(@PathVariable String id, @RequestBody LoanTypeRequest loanTypeRequest){
        LoanTypeResponse loanTypeResponse=loanTypeService.updateLoanTypePut(loanTypeRequest,id);
        CommonResponse<LoanTypeResponse> loanTypeResponseCommonResponse=generateCommonResponse(HttpStatus.OK.value(), "Loan Type Updated Successfully", Optional.of(loanTypeResponse));
        return ResponseEntity.ok(loanTypeResponseCommonResponse);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<LoanTypeResponse>> updateLoanTypePatch(@PathVariable String id, @RequestBody LoanTypeRequest loanTypeRequest){
        LoanTypeResponse loanTypeResponse=loanTypeService.updateLoanTypePatch(loanTypeRequest,id);
        CommonResponse<LoanTypeResponse> loanTypeResponseCommonResponse=generateCommonResponse(HttpStatus.OK.value(), "Loan Type Updated Successfully", Optional.of(loanTypeResponse));
        return ResponseEntity.ok(loanTypeResponseCommonResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    private ResponseEntity<CommonResponse<?>> deleteLoanType(@PathVariable String id){
        loanTypeService.deleteLoanType(id);
        CommonResponse<?> commonResponse=generateCommonResponse(HttpStatus.OK.value(), "Delete Loan Type Success", Optional.empty());
        return ResponseEntity.ok(commonResponse);
    }
}
