package com.news.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.news.entity.News;
import com.news.news.repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    // Get all news for a specific category
    public List<News> getNewsByCategory(String category) {
        return newsRepository.findByCategory(category);
    }

     // Get the latest news for a specific category
     public News getLatestNewsByCategory(String category) {
        return newsRepository.findFirstByCategoryOrderByIdAsc(category);
    }
 
 
}
