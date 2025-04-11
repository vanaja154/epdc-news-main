package com.news.news.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.news.entity.HomePageContent;

public interface HomePageContentRepository extends MongoRepository<HomePageContent, String> {

    List<HomePageContent> findByCategory(String category); // Fetch all news for a specific category

    HomePageContent findFirstByCategoryOrderByIdAsc(String category); // Fetch the latest news for a specific category

    
}
