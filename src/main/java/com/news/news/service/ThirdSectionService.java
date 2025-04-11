package com.news.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.news.entity.ThirdSection;
import com.news.news.repository.ThirdSectionRepository;

@Service
public class ThirdSectionService {

    @Autowired
    private ThirdSectionRepository contentRepository;

    public List<ThirdSection> getAllContent() {
        return contentRepository.findAll();
    }

    public void saveContent(ThirdSection content) {
        contentRepository.save(content);
    }

    public ThirdSection getContentById(String id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

}
