package com.spring.boot.api.controller;

import com.spring.boot.api.models.RolesModel;
import com.spring.boot.api.service.RolesService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class RolesController {

    @Autowired
    private RolesService rs;

    Map<Object, Object> json = new HashMap<>();

    @GetMapping
    public ResponseEntity<?> index() {

        ArrayList<RolesModel> roles = (ArrayList<RolesModel>) rs.getAll();

        if (roles.isEmpty()) {

            json.clear();
            json.put("status", "ERROR");
            json.put("code", HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("roles", roles);

            return new ResponseEntity<>(json, HttpStatus.OK);

        }
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<?> paginate(@PathVariable int page) {

        Pageable datos = PageRequest.of(page, 15);

        if (datos == null) {

            json.clear();
            json.put("status", "error");
            json.put("code", 404);
            json.put("message", "No hay datos o la pagina no existe");

            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("roles", rs.getAllByPage(datos));

            return new ResponseEntity<>(json, HttpStatus.OK);

        }

    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody RolesModel rolesModel) {

        return new ResponseEntity<>("ok", HttpStatus.OK);

    }

}
