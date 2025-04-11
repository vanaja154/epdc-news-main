package com.news.news.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.news.entity.AndhraPradesh;

public interface AndhraPradeshRepository extends MongoRepository<AndhraPradesh, String> {

 // Custom method to find by detailName
    List<AndhraPradesh> findByDetailName(String detailName);
    
}
