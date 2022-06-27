package com.spring.boot.api.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CRUD<Model> {

    public List<Model> getAllClientes();

    public Page<Model> getAllClientesByPage(Pageable pageable);

    public Model SaveData(Model model);

    public void DeleteDataByID(Long id);

    public Model FindById(Long id);

}
