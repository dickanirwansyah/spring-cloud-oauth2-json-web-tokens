package com.dicka.cloudoauth2resource.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "person_details")
public class PersonDetails extends BaseEntityId implements Serializable{

    private String gender;
    private String address;
    private String age;
    private String phoneNumber;
    private String phoneHome;
    private String zipCode;
    private String instagram;
    private String twitter;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

}
