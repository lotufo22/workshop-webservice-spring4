package com.coursejava.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursejava.webservice.entities.User;

// @Repository - Não há necessidade, pois está herdando JpaRepository que automaticamente
//já faz o registro como repository
public interface UserRepository extends JpaRepository<User, Long>{

}
