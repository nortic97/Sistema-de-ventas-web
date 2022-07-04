package com.spring.boot.api.dao;

import com.spring.boot.api.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersDao extends JpaRepository<UsersModel, Long> {

    @Query(value = "SELECT id FROM users WHERE user_name=?1", nativeQuery = true)
    public UsersModel getUserIdByUserName(String user);

}
