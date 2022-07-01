package com.spring.boot.api.controller;

import com.spring.boot.api.models.RolesModel;
import com.spring.boot.api.service.RolesService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    @Autowired
    private RolesService rs;

    Map<Object, Object> json = new HashMap<>();
    Map<Object, Object> errorVlidator = new HashMap<>();
    List<Object> errors = new ArrayList<>();

    @GetMapping
    public ResponseEntity<?> index() {

        ArrayList<RolesModel> roles = (ArrayList<RolesModel>) rs.getAll();

        if (roles.isEmpty()) {

            json.clear();
            json.put("status", "ERROR");
            json.put("code", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("roles", roles);

            return new ResponseEntity<>(json, HttpStatus.OK);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        RolesModel roles = rs.FindById(id);

        if (roles == null) {

            json.clear();
            json.put("status", "error");
            json.put("code", 404);
            json.put("message", "No se encuentra el rol");

            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("roles", roles);

            return new ResponseEntity<>(json, HttpStatus.OK);

        }

    }

    @GetMapping("/page") //request param equivale a /page?number=1
    public ResponseEntity<?> paginate(@RequestParam int number) {

        Pageable datos = PageRequest.of(number, 15);

        Page<RolesModel> roles = rs.getAllByPage(datos);

        if (number > (roles.getTotalPages() - 1)) {

            json.clear();
            json.put("status", "error");
            json.put("code", 404);
            json.put("message", "La pagina no existe");

            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("roles", roles);

            return new ResponseEntity<>(json, HttpStatus.OK);

        }

    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody RolesModel rolesModel, BindingResult result) {
        
        if (result.hasErrors()) {
            
            errorVlidator.clear();

            for (FieldError error : result.getFieldErrors()) {
                
                errorVlidator.put(error.getField(), error.getDefaultMessage());

            }

            json.clear();
            json.put("status", "error");
            json.put("code", 400);
            json.put("Errors", errorVlidator);
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        } else {

            try {

                rolesModel.setRole_name(rolesModel.getRole_name().toUpperCase().trim());

                RolesModel rol = rs.SaveData(rolesModel);

                json.clear();
                json.put("status", "sucess");
                json.put("code", 200);
                json.put("message", "El rol se ha guardado");
                json.put("Data", rol);
                
                return new ResponseEntity<>(json, HttpStatus.OK);

            } catch (DataAccessException ex) {

                errors.clear();
                errors.add(ex.getMostSpecificCause().getMessage());

                json.clear();
                json.put("status", "error");
                json.put("code", 400);
                json.put("errors", errors);
                return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

            }

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {

        RolesModel role = rs.FindById(id);

        if (role == null) {

            json.clear();
            json.put("status", "error");
            json.put("code", 404);
            json.put("message", "El rol no existe");

            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        } else {

            rs.DeleteDataByID(id);

            json.clear();
            json.put("status", "sucess");
            json.put("code", 200);
            json.put("message", "Rol eliminado");
            json.put("data", role);

            return new ResponseEntity<>(json, HttpStatus.OK);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody RolesModel rolesModel, BindingResult result, @PathVariable Long id) {

        RolesModel role = rs.FindById(id);

        if (role == null) {

            json.clear();
            json.put("status", "error");
            json.put("code", 404);
            json.put("message", "No es encuentra el dato");
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        } else {

            if (result.hasErrors()) {

                for (FieldError error : result.getFieldErrors()) {

                    errorVlidator.clear();
                    errorVlidator.put(error.getField(), error.getDefaultMessage());

                }

                json.clear();
                json.put("status", "error");
                json.put("code", 400);
                json.put("Errors", errorVlidator);
                return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

            } else {

                try {

                    role.setRole_name(rolesModel.getRole_name().toUpperCase());

                    RolesModel rol = rs.SaveData(role);

                    json.clear();
                    json.put("status", "sucess");
                    json.put("code", 200);
                    json.put("message", "El rol se ha actualizado");
                    json.put("Data", rol);
                    return new ResponseEntity<>(json, HttpStatus.OK);

                } catch (DataAccessException ex) {

                    errors.clear();
                    errors.add(ex.getMostSpecificCause().getMessage());

                    json.clear();
                    json.put("status", "error");
                    json.put("code", 400);
                    json.put("errors", errors);
                    return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

                }
            }
        }

    }

}
