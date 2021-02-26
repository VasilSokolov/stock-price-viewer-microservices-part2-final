package com.techprimers.stock.dbservice.mapper;

public interface Mapper<E, D> {

	E dtoToObject(D dto);
	
	D dataMapToObject(D map);
}
