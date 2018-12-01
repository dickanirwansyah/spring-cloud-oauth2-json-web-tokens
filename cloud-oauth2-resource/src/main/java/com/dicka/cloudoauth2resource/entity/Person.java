package com.dicka.cloudoauth2resource.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@Builder
@Table(name = "person")
@Entity
public class Person extends BaseEntityId implements Serializable{

    private String username;

    @Email
    private String email;
    private String password;
    private boolean active;

    @OneToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "person")
    private PersonDetails personDetails;
}
