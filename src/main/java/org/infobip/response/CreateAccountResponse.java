package org.infobip.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateAccountResponse {

    boolean status;
    String description;
    String password;

    public CreateAccountResponse(boolean status, String description, String password) {
        this.status = status;
        this.description = description;
        this.password = password;
    }

    public CreateAccountResponse(boolean status, String description) {
        this.status = status;
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
