package com.cursojava.aulamongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.aulamongo.domain.User;
import com.cursojava.aulamongo.dto.UserDTO;
import com.cursojava.aulamongo.repository.UserRepository;
import com.cursojava.aulamongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	//Instancia objeto do tipo UserRepository. 
	//@Autowired: o Spring instancia automatico (Injecao de dependencia)	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
		return;
	}
	
//Polemica. Por que nao colocou na classe UserDTO ? Poderia,
//mas foi incluido no UserService porque dependendo da situação, para instanciar o User
//podera ser necessario acessar o banco de dados (private UserRepository repo)
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
		

}
