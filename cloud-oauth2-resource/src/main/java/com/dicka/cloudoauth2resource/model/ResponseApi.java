package com.dicka.cloudoauth2resource.model;

public class ResponseApi {

    private String message;
    private String code;

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }
}
