package com.news.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.news.entity.AndhraPradesh;
import com.news.news.repository.AndhraPradeshRepository;

@Service
public class AndhraPradeshService {
    
    @Autowired
    private AndhraPradeshRepository contentRepository;

    public List<AndhraPradesh> getAllContent() {
        return contentRepository.findAll();
    }


    public List<AndhraPradesh> getByDetailName(String detailName) {
        return contentRepository.findByDetailName(detailName);
    }

    public void saveContent(AndhraPradesh content) {
        contentRepository.save(content);
    }

    public AndhraPradesh getContentById(String id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }



}
