package com.upgrad.eshop.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserdefinedResponse {
    private String errorMessage;
    private int statusCode;
    public UserdefinedResponse(){}
    public UserdefinedResponse(String eMessage,int sCode){
        this.errorMessage=eMessage;
        this.statusCode=sCode;
    }
}
