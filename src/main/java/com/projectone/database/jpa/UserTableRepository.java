package com.projectone.database.jpa;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projectone.model.UserRegisterationDetails;

public interface UserTableRepository extends JpaRepository<UserRegisterationDetails, Long> {

    List<UserRegisterationDetails> findByName(String name);
    
    //@Query("select u from USER_TABLE u where u.name = ?1")
    //List<UserRegisterationDetails> findByName(String name);
}