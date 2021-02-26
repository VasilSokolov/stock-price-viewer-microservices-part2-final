package com.techprimers.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.stock.dbservice.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>  {
	List<Shop> findByName(String name);
//	Shop findById(Long id);
	List<Shop> findById(Long id);
}

