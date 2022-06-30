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

@Data
@Entity
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "pcm", "odm"})
public class ProductsModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo es nulo")
    @NotBlank(message = "El campo esta vacio")
    private String name;

    @NotNull(message = "El campo es nulo")
    @NotBlank(message = "El campo esta vacio")
    private String code;

    @NotNull(message = "El campo es nulo")
    @NotBlank(message = "El campo esta vacio")
    @Pattern(message = "El campo no acepta letras", regexp = "^[0-9]*$")
    private Double price;

    @NotNull(message = "El campo es nulo")
    @NotBlank(message = "El campo esta vacio")
    private String description;

    @NotNull(message = "El campo es nulo")
    @NotBlank(message = "El campo esta vacio")
    @Pattern(message = "El campo no acepta letras", regexp = "^[0-9]*$")
    private int stock;

    private String image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductsCategoriesModel> pcm;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrdersDetailsModel> odm;

}
