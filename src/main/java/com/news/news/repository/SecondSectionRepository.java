package com.news.news.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.news.entity.SecondSection;

public interface SecondSectionRepository extends MongoRepository<SecondSection, String> {
}
