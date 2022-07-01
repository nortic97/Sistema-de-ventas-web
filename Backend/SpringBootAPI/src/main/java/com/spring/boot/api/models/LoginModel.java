package com.spring.boot.api.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginModel {

    @NotBlank(message = "Campo vacio")
    @NotNull(message = "Campo nulo")
    private String user_name;
    
    @NotBlank(message = "Campo vacio")
    @NotNull(message = "Campo nulo")
    private String password;
    
    private String token;

}
