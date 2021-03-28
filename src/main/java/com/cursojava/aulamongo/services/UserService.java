package com.cursojava.aulamongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.aulamongo.domain.User;
import com.cursojava.aulamongo.repository.UserRepository;

@Service
public class UserService {

	//Instancia objeto do tipo UserRepository. 
	//@Autowired: o Spring instancia automatico (Injecao de dependencia)	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
}
