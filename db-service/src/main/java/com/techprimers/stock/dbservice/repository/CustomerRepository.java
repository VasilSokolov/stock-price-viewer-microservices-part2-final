package com.techprimers.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.stock.dbservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>  {
	List<Customer> findByName(String name);
}
