package com.news.news.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.news.entity.Epaper;


public interface EpaperRepository extends MongoRepository<Epaper, String> {
    Epaper findFirstByOrderByIdAsc();
    
   
}
