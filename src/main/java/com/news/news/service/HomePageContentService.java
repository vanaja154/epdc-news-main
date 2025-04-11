package com.news.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.news.entity.HomePageContent;
import com.news.news.repository.HomePageContentRepository;

@Service
public class HomePageContentService {

     @Autowired
    private HomePageContentRepository contentRepository;

    public List<HomePageContent> getAllContent() {
        return contentRepository.findAll();
    }

    public void saveContent(HomePageContent content) {
        contentRepository.save(content);
    }

    public HomePageContent getContentById(String id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

}
