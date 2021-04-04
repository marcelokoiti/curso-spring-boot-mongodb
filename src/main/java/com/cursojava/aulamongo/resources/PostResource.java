package com.cursojava.aulamongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursojava.aulamongo.domain.Post;
import com.cursojava.aulamongo.resources.util.URL;
import com.cursojava.aulamongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);                 // Retorna Post
		
		return ResponseEntity.ok().body(obj);
	}

	// O valor a ser pesquisado sera passado como parametro
	// value="text" é o nome do parametro passado
	@RequestMapping(value="/titlesearch",method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		// decode do parametro
		text = URL.decodeParam(text);
		
		List<Post> list = service.findByTitle(text);
		
		return ResponseEntity.ok().body(list);
	}
}
