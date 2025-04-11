package com.news.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.news.news.entity.HomePageContent;
import com.news.news.entity.SecondSection;
// import com.news.news.repository.HomePageContentRepository;
import com.news.news.repository.SecondSectionRepository;

@Service
public class SecondSectionService {

    @Autowired
    private SecondSectionRepository contentRepository;

    public List<SecondSection> getAllContent() {
        return contentRepository.findAll();
    }

    public void saveContent(SecondSection content) {
        contentRepository.save(content);
    }

    public SecondSection getContentById(String id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

}
