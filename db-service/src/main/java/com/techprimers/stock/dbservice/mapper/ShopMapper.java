package com.techprimers.stock.dbservice.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.techprimers.stock.dbservice.model.Shop;
import com.techprimers.stock.dbservice.util.DataMap;
import com.techprimers.stock.dbservice.util.DateFormatUtils;

public class ShopMapper implements Mapper<Shop, Map<String, Object>> {

	@Override
	public Shop dtoToObject(Map<String, Object> dto) {
		DataMap dataMap = new DataMap(dto);
		Map shop = dataMap.get("shop");
		Shop shopEntiry = new Shop();
		shopEntiry.setId(shop.get("id") != null ? Long.valueOf(shop.get("id").toString()) : null);
		shopEntiry.setCity(shop.get("city").toString());
		shopEntiry.setName(shop.get("name").toString());
//		shopEntiry.setLocalDate(DateFormatUtils.formatString2LocalDateTime(shop.get("localDate").toString()));
		List<Map> cakes = (List<Map>) shop.get("cakes");
		cakes.forEach((cake) -> shopEntiry.getCakes().add(new CakeMapper().dtoToObject(cake))); 
//		for (Map cake : cakes) {
//			s.getCakes().add(new CakeMapper().dtoToObject(cake));
//		}
		return shopEntiry;
	}

	@Override
	public Map<String, Object> dataMapToObject(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
