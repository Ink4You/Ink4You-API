package br.com.bandtec.ink4yousembanco.uteis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseImgur {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("status")
    private String isValid;
    @JsonProperty("data")
    private ImgurResponseData data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public ImgurResponseData getData() {
        return data;
    }

    public void setData(ImgurResponseData data) {
        this.data = data;
    }
}
