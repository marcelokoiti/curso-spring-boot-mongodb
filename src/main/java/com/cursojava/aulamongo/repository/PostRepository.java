package com.cursojava.aulamongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cursojava.aulamongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//Da classe Post, no  atributo title
	//Automaticamente o Spring Data monta a consulta
//	List<Post> findByTitleContains(String text);

	//Ignora Maiuscula/minuscula
	List<Post> findByTitleContainsIgnoreCase(String text);
	
	//?0 - substitui pelo primeiro parametro text
	//i  - ignorecase
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
}
