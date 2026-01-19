package com.coursejava.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursejava.webservice.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
