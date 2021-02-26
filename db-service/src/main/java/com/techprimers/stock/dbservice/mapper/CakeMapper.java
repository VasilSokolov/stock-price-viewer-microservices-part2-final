package com.techprimers.stock.dbservice.mapper;

import java.util.List;
import java.util.Map;

import com.techprimers.stock.dbservice.model.Cake;
import com.techprimers.stock.dbservice.model.Customer;

public class CakeMapper implements Mapper<Cake, Map<String, Object>> {

	@Override
	public Cake dtoToObject(Map<String, Object> dto) {
//		DataMap dataMap = new DataMap(dto);
//		Customer c1 = new Customer(dto.get("name").toString());
//		Customer c2 = new Customer(dto.get("color").toString());
		Cake cakeEntiry = new Cake();
		List<Map> customers = (List<Map>) dto.get("customers");
		cakeEntiry.setName(dto.get("name").toString());
		cakeEntiry.setColor(dto.get("color").toString());

		customers.forEach((customer) -> 
		{
			Customer c = new CustomerMapper().dtoToObject(customer);
			cakeEntiry.getCustomers().add(c);
			c.getCakes().add(cakeEntiry);
		});
		
//		customers.forEach(s ->customer.getCakes().add(cakeEntiry));
//		cake.getCustomers().add(c1);
//		cake.getCustomers().add(c2);
//		for (Map map : customers) {
//			map
//		}
//		c1.getCakes().add(cake);
//		c2.getCakes().add(cake);
		return cakeEntiry;
	}

	@Override
	public Map<String, Object> dataMapToObject(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
