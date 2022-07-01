package com.spring.boot.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer","person"})
public class UsersModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Campo vacio")
    @NotNull(message = "Campo nulo")
    private String user_name;
    
    @NotBlank(message = "Campo vacio")
    @NotNull(message = "Campo nulo")
    @Email(message = "Email invalido")
    private String mail;
    
    @NotBlank(message = "Campo vacio")
    @NotNull(message = "Campo nulo")
    private String password;
    //private Long roles_id;
    @Temporal(TemporalType.DATE)
    private Date created_at;
    @Temporal(TemporalType.DATE)
    private Date updated_at;

    @JoinColumn(name="roles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RolesModel rol;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonsModel> person;
    
}
