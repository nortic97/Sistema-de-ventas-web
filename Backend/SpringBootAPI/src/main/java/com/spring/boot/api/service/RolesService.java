package com.spring.boot.api.service;

import com.spring.boot.api.dao.RolesDao;
import com.spring.boot.api.models.RolesModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolesService implements CRUD<RolesModel> {

    @Autowired
    private RolesDao rd;

    @Override
    public List<RolesModel> getAll() {

        return rd.findAll();

    }

    @Override
    public Page<RolesModel> getAllByPage(Pageable pageable) {

        return rd.findAll(pageable);

    }

    @Override
    public RolesModel SaveData(RolesModel model) {
        
        return rd.save(model);

    }

    @Override
    public void DeleteDataByID(Long id) {
        
        rd.deleteById(id);
        
    }

    @Override
    public RolesModel FindById(Long id) {
        
        return rd.findById(id).orElse(null);
        
    }
    
    public RolesModel getRoleIdByRoleName(String role){
        return rd.getRoleIdByRoleName(role);
    }

}
