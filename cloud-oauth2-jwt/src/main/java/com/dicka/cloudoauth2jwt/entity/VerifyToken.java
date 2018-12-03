package com.dicka.cloudoauth2jwt.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "verify_token")
public class VerifyToken extends BaseIdEntity implements Serializable{

    
}
