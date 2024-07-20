package com.enigma.dto.request;

import com.enigma.entity.InstalmentType;

public class InstalmentTypeRequest {
    private InstalmentType.EInstalmentType instalmentType;

    public InstalmentType.EInstalmentType getInstalmentType() {
        return instalmentType;
    }

    public void setInstalmentType(InstalmentType.EInstalmentType instalmentType) {
        this.instalmentType = instalmentType;
    }
}
