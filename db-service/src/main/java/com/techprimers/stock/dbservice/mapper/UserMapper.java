package com.techprimers.stock.dbservice.mapper;

import java.util.Map;

import com.techprimers.stock.dbservice.model.User;
import com.techprimers.stock.dbservice.util.DataMap;

public class UserMapper implements Mapper<User, Map<String, Object>> {

	@Override
	public User dtoToObject(Map<String, Object> map) {
		DataMap mapData = new DataMap(map.get("user"));
//		User user = new User();
//    	Map city = mapData.get("city");
//    	Map user1 = (Map) city.get("user");
//    	user.setFirstName(user1.get("firstName").toString());
//    	user.setLastName((String) user1.get("lastName"));
//    	user.setUserName((String) user1.get("name"));
//    	user.setAge((int) user1.get("age"));
    	User user = mapData.dtoToObject(User.class);
		return user;
	}
	
//	  {
//	    	"city": {
//	    		"user": {
//						"name": "So",
//						"firstName": "Sof",
//						"lastName": "Sofia",
//	    			"age": 25
//	    		}
//				}
//		}

	@Override
	public Map<String, Object> dataMapToObject(Map<String, Object> map) {
		
		
		return null;
	}

}
