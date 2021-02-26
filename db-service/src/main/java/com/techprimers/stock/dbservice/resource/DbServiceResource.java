package com.techprimers.stock.dbservice.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.stock.dbservice.mapper.CakeMapper;
import com.techprimers.stock.dbservice.mapper.CustomerMapper;
import com.techprimers.stock.dbservice.mapper.ShopMapper;
import com.techprimers.stock.dbservice.mapper.UserMapper;
import com.techprimers.stock.dbservice.model.Cake;
import com.techprimers.stock.dbservice.model.Customer;
import com.techprimers.stock.dbservice.model.Quote;
import com.techprimers.stock.dbservice.model.Quotes;
import com.techprimers.stock.dbservice.model.Shop;
import com.techprimers.stock.dbservice.model.User;
import com.techprimers.stock.dbservice.repository.CakeRepository;
import com.techprimers.stock.dbservice.repository.CustomerRepository;
import com.techprimers.stock.dbservice.repository.QuotesRepository;
import com.techprimers.stock.dbservice.repository.ShopRepository;
import com.techprimers.stock.dbservice.repository.UserRepository;
import com.techprimers.stock.dbservice.util.DataMap;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

	Session session;
	private QuotesRepository quotesRepository;
	private UserRepository userRepository;
	private ShopRepository shopRepository;
	private CakeRepository cakeRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public DbServiceResource(QuotesRepository quotesRepository, UserRepository userRepository,
			ShopRepository shopRepository, CakeRepository cakeRepository, CustomerRepository customerRepository) {
		this.quotesRepository = quotesRepository;
		this.userRepository = userRepository;
		this.shopRepository = shopRepository;
		this.cakeRepository = cakeRepository;
		this.customerRepository = customerRepository;
	}

	@GetMapping("/quote/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {

		return getQuotesByUserName(username);
	}

	@GetMapping("/user/{username}")
	public List<User> getUsers(@PathVariable("username") final String username) {

		return getUsersByUserName(username);
	}

	@PostMapping("/user/add")
	public List<User> add(@RequestBody Map<String, Object> map) {

		User user = new UserMapper().dtoToObject(map);
		Customer customer = new CustomerMapper().dtoToObject(map);
		user.setCustomer(customer);
		User u = userRepository.saveAndFlush(user);
//    	Customer c = customerRepository.save(customer);
//    	customer.setUser(user);
//    	getUsersAndCustomers(u, c);
		return getUsersById(u.getId());
	}

	@PostMapping("/shop/add")
	public ResponseEntity<?> addShop(@RequestBody Map<String, Object> map) {
		Shop shopResult = null;
		DataMap data = new DataMap(map.get("shop"));
		Shop shop = data.dtoToObject(Shop.class);
//		Shop shop = new ShopMapper().dtoToObject(map);
		if (shop.getId() != null) {
			Optional<Shop> shopEntity = Optional.of(getShopById(shop.getId()));
			if (shopEntity.isPresent()) {
				shop.setId(shop.getId());
				shopResult = shopRepository.save(shop);
			}
		} else {
			shopResult = shopRepository.save(shop);
		}

		return ResponseEntity.ok(shopResult);
	}

//	{
//		"shop": {
//			"number":6,
//			"name":"какскдкас",
//			"city":"Vidin 7",
//			"localDate": "2021-01-12 12:10:22",
//			"cakes":[
//				{
//				"name":"blueberry 2 ",
//				"color":"blue",
//				"customers":[
//					{
//						"name": "Ivo"
//					},{
//						"name": "Aria"
//					}
//				]
//			},{
//				"name":"blackberry 6",
//				"color":"black",
//				"customers":[
//					{
//						"name": "Toni"
//					},{
//						"name": "Tino"
//					}
//				]
//			}]
//		}
//	}

	
	@PostMapping("/cake/add")
	public List<Cake> addCake(@RequestBody Map<String, Object> map) {
//    	shopRepository.getOne(map)
		Cake cake = new CakeMapper().dtoToObject(map);
		Cake c = cakeRepository.save(cake);

		return getCakeByName(c.getName());
	}


	@PostMapping("/quote/add")
	public List<String> add(@RequestBody Quotes quotes) {

		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUserName(), quote))
				.forEach(quote -> quotesRepository.save(quote));
		return getQuotesByUserName(quotes.getUserName());
	}

	@PostMapping("/quote/delete/{username}")
	public List<String> delete(@PathVariable("username") final String username) {

		List<Quote> quotes = quotesRepository.findByUserName(username);
		quotesRepository.delete(quotes);
		List<String> result = new ArrayList<>();
		if (getQuotesByUserName(username).isEmpty()) {
			result.add(username + " was deleted");
		} else {
			return getQuotesByUserName(username);
		}
		return result;
	}

	private List<String> getQuotesByUserName(@PathVariable("username") String username) {
		return quotesRepository.findByUserName(username).stream().map(Quote::getQuote).collect(Collectors.toList());
	}

	private List<Object> getUsersAndCustomers(List<User> users, List<Customer> customers) {
		List<Object> list = new ArrayList<Object>();
		list.addAll(users);
		list.addAll(customers);
		return list;

	}

	private List<User> getUsersById(@PathVariable("id") Long id) {
		return userRepository.findById(id);
	}

	private List<User> getUsersByUserName(@PathVariable("name") String name) {
		return userRepository.findByUserName(name);
	}

	private List<Customer> getCustomersByUserName(@PathVariable("name") String name) {
		return customerRepository.findByName(name);
	}

	private List<Shop> getShopsByName(@PathVariable("name") String name) {
		return shopRepository.findByName(name);
	}

	private Shop getShopById(@PathVariable("name") Long id) {
		return shopRepository.findOne(id);
	}

	private List<Shop> getShopsById(@PathVariable("name") Long id) {
		return shopRepository.findById(id);
	}

	private List<Cake> getCakeByName(@PathVariable("name") String name) {
		return cakeRepository.findByName(name);
	}

	@PostMapping("/user/delete/{username}")
	public List<User> deleteUser(@PathVariable("username") final String username) {

		List<User> users = userRepository.findByUserName(username);
		userRepository.delete(users);
		List<User> result = new ArrayList<>();
		if (getUsersByUserName(username).isEmpty()) {
			result.add(new User(username + " was deleted"));
		} else {
			return getUsersByUserName(username);
		}
		return result;
	}

	@GetMapping("/shop/delete/{id}")
	public ResponseEntity<?> deleteCake(@PathVariable("id") final Long id) {

		Shop shop = shopRepository.findOne(id);
		shopRepository.delete(shop);
		if (getShopsById(id).isEmpty()) {
			return ResponseEntity.ok(String.format("Id %d was deleted", getShopById(id)));
		}
		return ResponseEntity.ok(String.format("Id %d was not deleted", getShopById(id)));
	}
}
