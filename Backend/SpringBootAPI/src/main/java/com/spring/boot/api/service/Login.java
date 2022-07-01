
package com.spring.boot.api.service;

import com.spring.boot.api.models.UsersModel;


public interface Login {
    
    public UsersModel getUserByLogin(String user, String pass);
    
}
