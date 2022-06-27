package com.spring.boot.api.models;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UsersModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user_name;
    private String mail;
    private String password;
    private Long roles_id;
    private Timestamp created_at;
    private Timestamp updated_at;

    /* @Column(name="roles_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RolesModel roles;*/

}
