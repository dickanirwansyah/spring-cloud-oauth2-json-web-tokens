package com.dicka.springbootemail.controller;

import com.dicka.springbootemail.VerifyForm;
import com.dicka.springbootemail.service.VerifyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/")
public class AccountRestController {

    @Autowired
    private VerifyTokenService verifyTokenService;

    @PostMapping(value = "verified-email")
    public ResponseEntity<String> verifiedEmail(@Valid @RequestBody VerifyForm verifyForm){
        verifyTokenService.createVerification(verifyForm.getEmail());
        return new ResponseEntity<String>("verification-form",
                HttpStatus.OK);
    }
}