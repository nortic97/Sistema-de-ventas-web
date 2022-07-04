package com.spring.boot.api.controller;

import com.spring.boot.api.models.RolesModel;
import com.spring.boot.api.models.UsersModel;
import com.spring.boot.api.service.RolesService;
import com.spring.boot.api.service.UsersService;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
@RequestMapping("/api/usuarios")
public class UsersController {

    @Autowired
    private UsersService us;

    @Autowired
    private RolesService rs;

    Map<Object, Object> json = new LinkedHashMap<>();
    Map<Object, Object> errorsValidate = new LinkedHashMap<>();
    List<Object> errors = new ArrayList<>();

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

    @GetMapping("/page")
    public ResponseEntity<?> paginate(@RequestParam int number) {

        Pageable data = PageRequest.of(number, 15);

        Page<UsersModel> usuario = us.getAllByPage(data);

        if (number > (usuario.getTotalPages() - 1)) {

            json.clear();
            json.put("status", "error");
            json.put("code", 400);
            json.put("message", "La pagina no existe");

            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("usuarios", usuario);

            return new ResponseEntity<>(json, HttpStatus.OK);

        }

    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody UsersModel usersModel, BindingResult result, @RequestParam(required = false, defaultValue = "false") boolean register) {

        if (result.hasErrors()) {

            errorsValidate.clear();

            for (FieldError error : result.getFieldErrors()) {

                errorsValidate.put(error.getField(), error.getDefaultMessage());

            }

            json.clear();
            json.put("status", "error");
            json.put("code", 400);
            json.put("usuarios", errorsValidate);

            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        } else {

            try {

                if (register) {

                    RolesModel rol = rs.getRoleIdByRoleName("USUARIO");
                    usersModel.setRol(rol);

                } else {

                    RolesModel rol = rs.FindById(usersModel.getRol().getId());
                    usersModel.setRol(rol);

                }

                usersModel.setUser_name(usersModel.getUser_name().trim());
                usersModel.setMail(usersModel.getMail().trim());

                us.SaveData(usersModel);

                json.clear();
                json.put("status", "succes");
                json.put("code", 200);
                json.put("usuarios", usersModel);

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

    @DeleteMapping
    public ResponseEntity<?> destroy(@RequestParam Long id) {

        UsersModel user = us.FindById(id);

        if (user == null) {

            json.clear();
            json.put("status", "error");
            json.put("code", 400);
            json.put("message", "No exsite el dato a eliminar");
            json.put("search", id);
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        } else {

            us.DeleteDataByID(id);

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("message", "Eliminado");
            json.put("data", user);
            return new ResponseEntity<>(json, HttpStatus.OK);

        }

    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam Long id, @Valid @RequestBody UsersModel usersModel, BindingResult result) {

        UsersModel usuario = us.FindById(id);

        if (usuario == null) {

            json.clear();
            json.put("status", "error");
            json.put("code", 404);
            json.put("message", "No existe el usuario");
            json.put("search", id);
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        } else {

            if (result.hasErrors()) {

                errorsValidate.clear();

                for (FieldError error : result.getFieldErrors()) {

                    errorsValidate.put(error.getField(), error.getDefaultMessage());

                }

                json.clear();
                json.put("status", "error");
                json.put("code", 400);
                json.put("errors", errorsValidate);
                return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

            } else {

                try {

                    RolesModel rol = rs.FindById(usersModel.getRol().getId());

                    usuario.setUser_name(usersModel.getUser_name().trim());
                    usuario.setMail(usersModel.getMail().trim());
                    usuario.setUpdated_at(new Date());
                    usuario.setRol(rol);

                    us.SaveData(usuario);

                    json.clear();
                    json.put("status", "succes");
                    json.put("code", 200);
                    json.put("message", "Actualizado");
                    json.put("usuario", usuario);
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
