package br.com.bandtec.ink4yousembanco.uteis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseWebScraper {
    @JsonProperty("ErrorMessage")
    private String errorMessage;
    @JsonProperty("IsValid")
    private boolean isValid;
    @JsonProperty("Data")
    private List<String> data;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}