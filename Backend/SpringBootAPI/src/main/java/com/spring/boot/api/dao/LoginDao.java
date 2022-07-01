
package com.spring.boot.api.dao;

import com.spring.boot.api.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LoginDao extends JpaRepository<UsersModel, Long>{
    
    @Query(value = "SELECT * FROM users WHERE user_name=?1 AND password=?2", nativeQuery = true)
    public UsersModel getLogin(String user, String password);
    
}
