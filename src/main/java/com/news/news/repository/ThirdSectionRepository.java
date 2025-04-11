package com.news.news.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.news.entity.ThirdSection;

public interface ThirdSectionRepository extends MongoRepository<ThirdSection, String> {
}
