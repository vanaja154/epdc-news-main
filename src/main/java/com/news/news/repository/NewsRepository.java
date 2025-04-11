package com.news.news.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.news.entity.News;

public interface NewsRepository extends MongoRepository<News, String> {
    List<News> findByCategory(String category); // Fetch all news for a specific category

    News findFirstByCategoryOrderByIdAsc(String category); // Fetch the latest news for a specific category
}
