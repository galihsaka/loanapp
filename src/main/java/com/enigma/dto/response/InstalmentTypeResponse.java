package com.enigma.dto.response;

import com.enigma.entity.InstalmentType;

public class InstalmentTypeResponse {
    private String id;
    private InstalmentType.EInstalmentType instalmentType;
    private String instalmentTypeStatus;

    public String getInstalmentTypeStatus() {
        return instalmentTypeStatus;
    }

    public void setInstalmentTypeStatus(String instalmentTypeStatus) {
        this.instalmentTypeStatus = instalmentTypeStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InstalmentType.EInstalmentType getInstalmentType() {
        return instalmentType;
    }

    public void setInstalmentType(InstalmentType.EInstalmentType instalmentType) {
        this.instalmentType = instalmentType;
    }
}
