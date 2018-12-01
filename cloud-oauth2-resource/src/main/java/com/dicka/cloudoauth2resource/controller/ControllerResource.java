package com.dicka.cloudoauth2resource.controller;

import com.dicka.cloudoauth2resource.model.CustomPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerResource {

    @PreAuthorize("hasAuthority('role_admin')")
    @GetMapping(value = "/api/admins")
    public String context(){
        CustomPrincipal principal = (CustomPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        /** test **/
        System.out.println("user principal : "+principal);
        return principal.getUsername() + " " + principal.getEmai();
    }

    @PreAuthorize("hasAnyAuthority('role_user', 'role_admin')")
    @GetMapping(value = "/api/users")
    public String secured(CustomPrincipal customPrincipal){
        return customPrincipal.getUsername() + " "+customPrincipal.getUsername();
    }

    @GetMapping(value = "/api/general")
    public String general(){
        return "api success ";
    }
}
