package com.dicka.springbootemail;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class Users implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Email(message = "email not valid.")
    private String email;
    private boolean isActive;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private VerifyToken verifyToken;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public boolean isActive(){
        return isActive;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    public VerifyToken getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(VerifyToken verifyToken) {
        this.verifyToken = verifyToken;
    }
}
