package com.news.news.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.news.entity.Epaper;
import com.news.news.repository.EpaperRepository;


@Service
public class EpaperService {

    @Autowired
    private EpaperRepository contentRepository;

    public List<Epaper> getAllContent() {
        return contentRepository.findAll();
    }


   public Epaper getLatestContent() {
        return contentRepository.findFirstByOrderByIdAsc(); // Fetch latest content
    }
}
