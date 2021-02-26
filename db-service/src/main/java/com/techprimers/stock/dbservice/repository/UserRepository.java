package com.techprimers.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.stock.dbservice.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	List<User> findById(Long id);
	List<User> findByUserName(String username);
}
