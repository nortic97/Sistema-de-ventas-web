
package com.spring.boot.api.dao;

import com.spring.boot.api.models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesDao extends JpaRepository<RolesModel, Long> {
    
}
