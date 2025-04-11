package com.news.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.news.entity.FourthSection;
import com.news.news.repository.FourthSectionRepository;

@Service
public class FourthSectionService {

    @Autowired
    private FourthSectionRepository contentRepository;

    public List<FourthSection> getAllContent() {
        return contentRepository.findAll();
    }

    public void saveContent(FourthSection content) {
        contentRepository.save(content);
    }

    public FourthSection getContentById(String id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }


}
