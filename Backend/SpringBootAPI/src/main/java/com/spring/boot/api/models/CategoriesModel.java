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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Entity
@Table(name = "categories")
@JsonIgnoreProperties({"hibernateLazyInitializer","pcm"})
public class CategoriesModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo es nulo")
    @NotBlank(message = "El campo esta vacio")
    @Pattern(message = "El campo no acepta numeros", regexp = "^[A-Za-z]*$")
    @UniqueElements(message = "El campo ya existe")
    private String category_name;

    @NotNull(message = "El campo es nulo")
    @NotBlank(message = "El campo esta vacio")
    private String description;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ProductsCategoriesModel> pcm; 

}
