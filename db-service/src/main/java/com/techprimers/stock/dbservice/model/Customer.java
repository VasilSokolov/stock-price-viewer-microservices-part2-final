package com.techprimers.stock.dbservice.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customers", catalog = "test")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "customer_cake",
				joinColumns = {@JoinColumn(name = "customer_id")},
				inverseJoinColumns = {@JoinColumn(name = "cake_id")})				
	private List<Cake> cakes = new ArrayList<>();
	


//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
//	private User user;
	
	public Customer() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}


	public List<Cake> getCakes() {
		return cakes;
	}



	public void setCakes(List<Cake> cakes) {
		this.cakes = cakes;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
