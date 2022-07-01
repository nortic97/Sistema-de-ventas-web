package com.spring.boot.api.controller;

import com.spring.boot.api.dao.LoginDao;
import com.spring.boot.api.models.LoginModel;
import com.spring.boot.api.models.PersonsModel;
import com.spring.boot.api.models.UsersModel;
import com.spring.boot.api.service.LoginService;
import com.spring.boot.api.service.PersonsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService ls;
    
    @Autowired
    private PersonsService ps;

    Map<Object, Object> json = new HashMap<>();
    Map<Object, Object> errorsValidate = new HashMap<>();

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginModel login, BindingResult result) {

        if (result.hasErrors()) {

            errorsValidate.clear();

            for (FieldError errors : result.getFieldErrors()) {

                errorsValidate.put(errors.getField(), errors.getDefaultMessage());

            }

            json.clear();
            json.put("status", "error");
            json.put("code", 400);
            json.put("errors", errorsValidate);
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        } else {

            String username = login.getUser_name();
            String password = login.getPassword();

            UsersModel usuario = ls.getUserByLogin(username, password);

            if (usuario != null) {

                String token = getJWTToken(usuario);

                return new ResponseEntity<>(token, HttpStatus.OK);

            } else {

                return new ResponseEntity<>("error", HttpStatus.OK);

            }

        }

    }

    private String getJWTToken(UsersModel usuario) {

        String secretKey = "1234567890";

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(usuario.getRol().getRole_name());

        String token = Jwts
                .builder()
                .setId(usuario.getId().toString())
                .setSubject(usuario.getUser_name())
                .claim("name", usuario.getPerson().get(0).getName())
                .claim("surname", usuario.getPerson().get(0).getSurname())
                .claim("image", usuario.getPerson().get(0).getImage())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;

    }

}
