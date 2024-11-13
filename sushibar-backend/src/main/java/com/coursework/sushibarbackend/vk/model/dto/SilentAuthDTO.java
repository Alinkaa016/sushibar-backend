package com.coursework.sushibarbackend.vk.model.dto;

public class SilentAuthDTO {
    private String silentToken;
    private String type;
    private String uuid;

    public String getSilentToken() {
        return silentToken;
    }

    public void setSilentToken(String silentToken) {
        this.silentToken = silentToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
