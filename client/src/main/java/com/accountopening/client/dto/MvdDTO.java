package com.accountopening.client.dto;

import com.accountopening.client.enums.MVDAttribute;
import lombok.Builder;

@Builder
public class MvdDTO {
    private MVDAttribute mvdAttribute;
    private String status;
    private String message;

    public MvdDTO() {
    }

    public MvdDTO(MVDAttribute mvdAttribute, String status, String message) {
        this.mvdAttribute = mvdAttribute;
        this.status = status;
        this.message = message;
    }

    public MVDAttribute getMvdAttribute() {
        return mvdAttribute;
    }

    public void setMvdAttribute(MVDAttribute mvdAttribute) {
        this.mvdAttribute = mvdAttribute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
