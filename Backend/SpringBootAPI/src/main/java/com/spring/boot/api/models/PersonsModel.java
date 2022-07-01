
package com.spring.boot.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
import lombok.Data;

@Data
@Entity
@Table(name = "persons")
@JsonIgnoreProperties({"hibernateLazyInitializer","orders"})
public class PersonsModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UsersModel user;
    
    private String name;
    
    private String surname;
    
    private String DNI;
    
    private String image;
    
    private String address;
    
    private String phone;
    
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<OrdersModel> orders;
    
}
