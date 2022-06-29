
package com.spring.boot.api.controller;

import com.spring.boot.api.models.PersonsModel;
import com.spring.boot.api.models.RolesModel;
import com.spring.boot.api.service.PersonsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class PersonsController {
    
    @Autowired
    private PersonsService ps;
    
    Map<Object, Object> json = new HashMap<>();
    
    @GetMapping
    public ResponseEntity<?> index(){
        
        ArrayList<PersonsModel> persons = (ArrayList<PersonsModel>) ps.getAll();
        
        if(persons.isEmpty()){
            
            json.clear();
            json.put("status", "ERROR");
            json.put("code", HttpStatus.BAD_REQUEST);
            
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
            
        }else{
            
            json.clear();
            json.put("status", "success");
            json.put("code", 200);
            json.put("persons", persons);
            
            return new ResponseEntity<>(json, HttpStatus.OK);
            
        }
    }
}
