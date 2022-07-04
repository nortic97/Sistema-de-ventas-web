package com.spring.boot.api.service;

import com.spring.boot.api.dao.UsersDao;
import com.spring.boot.api.models.UsersModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements CRUD<UsersModel> {
    
    @Autowired
    private UsersDao ud;
    
    @Override
    public List<UsersModel> getAll() {
        
        return ud.findAll();
        
    }
    
    @Override
    public Page<UsersModel> getAllByPage(Pageable pageable) {
        
        return ud.findAll(pageable);
        
    }
    
    @Override
    public UsersModel SaveData(UsersModel model) {
        return ud.save(model);
    }
    
    @Override
    public void DeleteDataByID(Long id) {
        ud.deleteById(id);
    }
    
    @Override
    public UsersModel FindById(Long id) {
        return ud.findById(id).orElse(null);
    }
    
    public UsersModel getUserIdByUserName(String user) {
        return ud.getUserIdByUserName(user);
    }
    
}
