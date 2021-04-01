package com.cursojava.aulamongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursojava.aulamongo.domain.User;
import com.cursojava.aulamongo.dto.UserDTO;
import com.cursojava.aulamongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User>    list = service.findAll();            // Retorna User
		//Converte User para UserDTO utilizando expressao Lambda
		List<UserDTO> listDto = list.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);                 // Retorna User
		//Converte User para UserDTO   
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> Insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);              // Converte UserDto para User
		obj = service.insert(obj);
		// Para criar cabecalho com URL do recurso criado (pega endereco objeto inserido)
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);                            
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> Update(@RequestBody UserDTO objDto,@PathVariable String id ) {
		User obj = service.fromDTO(objDto);              // Converte UserDto para User
		obj.setId(id);                                  
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
}
