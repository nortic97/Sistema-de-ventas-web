package com.spring.boot.api.controller;

import com.spring.boot.api.models.UsersModel;
import com.spring.boot.api.service.UsersService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UsersController {

    @Autowired
    private UsersService us;

    Map<Object, Object> json = new HashMap<>();

    @GetMapping
    public ResponseEntity<?> index() {

        ArrayList<UsersModel> users = (ArrayList<UsersModel>) us.getAll();

        if (users.isEmpty()) {

            json.clear();
            json.put("status", "ERROR");
            json.put("code", 404);

            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("users", users);

            return new ResponseEntity<>(json, HttpStatus.OK);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        UsersModel user = us.FindById(id);

        if (user == null) {

            json.clear();
            json.put("status", "error");
            json.put("code", 404);
            json.put("message", "No se encuentra el dato");
            
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("user", user);
            
            return new ResponseEntity<>(json, HttpStatus.OK);

        }

    }

}
