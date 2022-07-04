package com.spring.boot.api.controller;

import com.spring.boot.api.models.PersonsModel;
import com.spring.boot.api.service.PersonsService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/personas")
public class PersonsController {

    @Autowired
    private PersonsService ps;

    Map<Object, Object> json = new LinkedHashMap<>();
    Map<Object, Object> errorsValidate = new LinkedHashMap<>();
    List<Object> errors = new ArrayList<>();

    @GetMapping
    public ResponseEntity<?> index() {

        ArrayList<PersonsModel> persons = (ArrayList<PersonsModel>) ps.getAll();

        if (persons.isEmpty()) {

            json.clear();
            json.put("status", "ERROR");
            json.put("code", HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("persons", persons);

            return new ResponseEntity<>(json, HttpStatus.OK);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        PersonsModel personas = ps.FindById(id);

        if (personas != null) {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("roles", personas);

            return new ResponseEntity<>(json, HttpStatus.OK);

        } else {

            json.clear();
            json.put("status", "error");
            json.put("code", 404);
            json.put("message", "No se encuentra la persona");

            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/page") //request param equivale a /page?number=1
    public ResponseEntity<?> paginate(@RequestParam int number) {

        Pageable datos = PageRequest.of(number, 15);

        Page<PersonsModel> personas = ps.getAllByPage(datos);

        if (number > (personas.getTotalPages() - 1)) {

            json.clear();
            json.put("status", "error");
            json.put("code", 404);
            json.put("message", "La pagina no existe");

            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        } else {

            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("personas", personas);

            return new ResponseEntity<>(json, HttpStatus.OK);

        }

    }

}
