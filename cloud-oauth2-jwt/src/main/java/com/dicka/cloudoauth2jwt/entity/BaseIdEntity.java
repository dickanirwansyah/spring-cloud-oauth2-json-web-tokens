package com.dicka.cloudoauth2jwt.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
