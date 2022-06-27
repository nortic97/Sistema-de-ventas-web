
package com.spring.boot.api.dao;

import com.spring.boot.api.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersDao extends JpaRepository<UsersModel, Long>{
    
    
    
}
