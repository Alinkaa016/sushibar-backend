package com.coursework.sushibarbackend.vk.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VkApiResponse {

    @JsonProperty("response")
    private ResponseData response;

    public ResponseData getResponse() {
        return response;
    }

    public void setResponse(ResponseData response) {
        this.response = response;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {

        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("access_token_id")
        private String accessTokenId;

        @JsonProperty("user_id")
        private int userId;

        @JsonProperty("additional_signup_required")
        private boolean additionalSignupRequired;

        @JsonProperty("is_partial")
        private boolean isPartial;

        @JsonProperty("is_service")
        private boolean isService;

        private String email;

        private int source;

        @JsonProperty("source_description")
        private String sourceDescription;

        @JsonProperty("expires_in")
        private int expiresIn;



        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessTokenId() {
            return accessTokenId;
        }

        public void setAccessTokenId(String accessTokenId) {
            this.accessTokenId = accessTokenId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public boolean isAdditionalSignupRequired() {
            return additionalSignupRequired;
        }

        public void setAdditionalSignupRequired(boolean additionalSignupRequired) {
            this.additionalSignupRequired = additionalSignupRequired;
        }

        public boolean isPartial() {
            return isPartial;
        }

        public void setPartial(boolean isPartial) {
            this.isPartial = isPartial;
        }

        public boolean isService() {
            return isService;
        }

        public void setService(boolean isService) {
            this.isService = isService;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public String getSourceDescription() {
            return sourceDescription;
        }

        public void setSourceDescription(String sourceDescription) {
            this.sourceDescription = sourceDescription;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }
    }
}
