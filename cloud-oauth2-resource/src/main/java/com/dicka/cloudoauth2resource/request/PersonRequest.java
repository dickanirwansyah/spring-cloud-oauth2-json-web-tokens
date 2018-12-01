package com.dicka.cloudoauth2resource.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonRequest {

    /**person field **/
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    /** person details field **/
    private String gender;
    private String address;
    private String age;
    private String phoneNumber;
    private String phoneHome;
    private String zipCode;
    private String instagram;
    private String twitter;

}
