
package com.spring.boot.api.service;

import com.spring.boot.api.dao.UsersDao;
import com.spring.boot.api.models.UsersModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements CRUD<UsersModel>{
    
    @Autowired
    private UsersDao ud;

    @Override
    public List<UsersModel> getAllClientes() {

        return ud.findAll();
        
    }

    @Override
    public Page<UsersModel> getAllClientesByPage(Pageable pageable) {

        return ud.findAll(pageable);
        
    }

    @Override
    public UsersModel SaveData(UsersModel model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void DeleteDataByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsersModel FindById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
