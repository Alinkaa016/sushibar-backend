package com.coursework.sushibarbackend.vk.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VkProfileInfo {

    @JsonProperty("response")
    private List<UserProfile> response;

    public List<UserProfile> getResponse() {
        return response;
    }

    public void setResponse(List<UserProfile> response) {
        this.response = response;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserProfile {

        @JsonProperty("id")
        private int id;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        @JsonProperty("can_access_closed")
        private boolean canAccessClosed;

        @JsonProperty("is_closed")
        private boolean isClosed;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public boolean isCanAccessClosed() {
            return canAccessClosed;
        }

        public void setCanAccessClosed(boolean canAccessClosed) {
            this.canAccessClosed = canAccessClosed;
        }

        public boolean isClosed() {
            return isClosed;
        }

        public void setClosed(boolean isClosed) {
            this.isClosed = isClosed;
        }
    }
}
