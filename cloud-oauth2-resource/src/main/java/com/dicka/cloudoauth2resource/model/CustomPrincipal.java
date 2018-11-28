package com.dicka.cloudoauth2resource.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class CustomPrincipal implements Serializable{

    private String username;
    private String email;

    public CustomPrincipal(String s, String toString, String s1, Collection<? extends GrantedAuthority> authorities){}

    public CustomPrincipal(String username, String email){
        this.username = username;
        this.email = email;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmai(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
