package com.techprimers.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.stock.dbservice.model.Cake;

public interface CakeRepository extends JpaRepository<Cake, Long>  {
	List<Cake> findByName(String name);
}
