package com.spring.boot.api.service;

import com.spring.boot.api.dao.LoginDao;
import com.spring.boot.api.models.RolesModel;
import com.spring.boot.api.models.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements Login {

    @Autowired
    private LoginDao ld;

    @Override
    public UsersModel getUserByLogin(String user, String pass) {
        return ld.getLogin(user, pass);
    }

}
