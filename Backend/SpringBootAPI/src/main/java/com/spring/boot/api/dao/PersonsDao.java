
package com.spring.boot.api.dao;

import com.spring.boot.api.models.PersonsModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonsDao extends JpaRepository<PersonsModel, Long>{
    
}
