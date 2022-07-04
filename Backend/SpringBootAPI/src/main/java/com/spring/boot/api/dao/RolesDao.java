package com.spring.boot.api.dao;

import com.spring.boot.api.models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolesDao extends JpaRepository<RolesModel, Long> {

    @Query(value = "SELECT * FROM roles WHERE role_name=?1", nativeQuery = true)
    public RolesModel getRoleIdByRoleName(String role);

}
