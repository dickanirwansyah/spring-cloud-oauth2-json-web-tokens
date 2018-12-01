package com.dicka.cloudoauth2resource.controller;

import com.dicka.cloudoauth2resource.entity.Person;
import com.dicka.cloudoauth2resource.request.PersonRequest;
import com.dicka.cloudoauth2resource.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PreAuthorize("hasAuthority('role_admin')")
    @PostMapping(value = "/api/person")
    public PersonRequest create(@RequestBody @Valid PersonRequest request,
                                         BindingResult bindingResult){

        return  personService.createNewPerson(request, bindingResult);
    }
}
