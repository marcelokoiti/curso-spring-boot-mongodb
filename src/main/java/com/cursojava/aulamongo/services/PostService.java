package com.cursojava.aulamongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.aulamongo.domain.Post;
import com.cursojava.aulamongo.repository.PostRepository;
import com.cursojava.aulamongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	//Instancia objeto do tipo UserRepository. 
	//@Autowired: o Spring instancia automatico (Injecao de dependencia)	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Post não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
//		return repo.findByTitleContains(text);
//		return repo.findByTitleContainsIgnoreCase(text);	
		return repo.searchTitle(text);
	}
}
