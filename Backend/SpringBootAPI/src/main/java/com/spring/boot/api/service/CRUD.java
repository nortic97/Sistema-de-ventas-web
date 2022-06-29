package com.spring.boot.api.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CRUD<Model> {

    public List<Model> getAll();

    public Page<Model> getAllByPage(Pageable pageable);

    public Model SaveData(Model model);

    public void DeleteDataByID(Long id);

    public Model FindById(Long id);

}
