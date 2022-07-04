package com.spring.boot.api.controller;

import com.spring.boot.api.models.LoginModel;
import com.spring.boot.api.models.UsersModel;
import com.spring.boot.api.service.LoginService;
import com.spring.boot.api.service.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static final int EXP = 1000 * 60 * 60 * 24 * 7;

    private String token = null;

    private final String secretKey = "Prueba";

    @Autowired
    private LoginService ls;

    @Autowired
    private UsersService us;

    Map<Object, Object> json = new LinkedHashMap<>();
    Map<Object, Object> errorsValidate = new LinkedHashMap<>();

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginModel login, BindingResult result, @RequestParam(name = "decode", required = false, defaultValue = "false") boolean decode) {

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

                if (token == null || "".equals(token)) {

                    token = getJWTToken(usuario);
                    login.setToken(token);

                } else {

                    login.setToken(token);

                }

                if (decode != false) {

                    Object user_data = decodeJwt(login.getToken());

                    return new ResponseEntity<>(user_data, HttpStatus.OK);

                } else {

                    json.clear();
                    json.put("jwt_token", login.getToken());

                    return new ResponseEntity<>(json, HttpStatus.OK);

                }

            } else {

                json.clear();
                json.put("status", "error");
                json.put("code", 400);
                json.put("message", "Usuario o contraseña invalidos");
                return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

            }

        }

    }

    private String getJWTToken(UsersModel usuario) {

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(usuario.getRol().getRole_name());

        String jwt = Jwts
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
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return jwt;

    }

    public Object decodeJwt(String token) {

        return Jwts
                .parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

}
