package com.spring.boot.api.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "order_details")
public class OrdersDetailsModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    private OrdersModel order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id", referencedColumnName = "id")
    private ProductsModel product;

    @NotNull(message = "El campo es nulo")
    @NotBlank(message = "El campo esta vacio")
    @Pattern(message = "El campo no acepta letras", regexp = "^[0-9]*$")
    private int quantity;

    @NotNull(message = "El campo es nulo")
    @NotBlank(message = "El campo esta vacio")
    private Double total;
}
