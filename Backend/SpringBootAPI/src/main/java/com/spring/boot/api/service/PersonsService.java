
package com.spring.boot.api.service;

import com.spring.boot.api.dao.PersonsDao;
import com.spring.boot.api.models.PersonsModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonsService implements CRUD<PersonsModel>{
    
    @Autowired
    private PersonsDao pd;

    @Override
    public List<PersonsModel> getAll() {
        return pd.findAll();
    }

    @Override
    public Page<PersonsModel> getAllByPage(Pageable pageable) {
        return pd.findAll(pageable);
    }

    @Override
    public PersonsModel SaveData(PersonsModel model) {
        return pd.save(model);
    }

    @Override
    public void DeleteDataByID(Long id) {
        pd.deleteById(id);
    }

    @Override
    public PersonsModel FindById(Long id) {
        return pd.findById(id).orElse(null);
    }
    
    
    
}
