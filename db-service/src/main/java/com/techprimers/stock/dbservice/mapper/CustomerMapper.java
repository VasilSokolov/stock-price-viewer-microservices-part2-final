package com.techprimers.stock.dbservice.mapper;

import java.util.Map;

import com.techprimers.stock.dbservice.model.Customer;
import com.techprimers.stock.dbservice.model.Shop;
import com.techprimers.stock.dbservice.util.DataMap;

public class CustomerMapper implements Mapper<Customer, Map<String, Object>> {

	@Override
	public Customer dtoToObject(Map<String, Object> dto) {
		DataMap data = new DataMap(dto.get("customer") != null ? dto.get("customer") : dto);
		Customer customer = data.dtoToObject(Customer.class);
//		Customer customer = new Customer();
//    	customer.setName(mapData.get("name"));
		return customer;
	}

	@Override
	public Map<String, Object> dataMapToObject(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
