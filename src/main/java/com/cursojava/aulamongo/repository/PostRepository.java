package com.cursojava.aulamongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cursojava.aulamongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
}
