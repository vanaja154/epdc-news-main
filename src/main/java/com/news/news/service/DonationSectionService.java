package com.news.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.news.entity.DonationSection;
import com.news.news.entity.Epaper;
import com.news.news.repository.DonationSectionRepository;
@Service
public class DonationSectionService {

    @Autowired
    private DonationSectionRepository contentRepository;

    public List<DonationSection> getAllContent() {
        return contentRepository.findAll();
    }

    public void saveContent(DonationSection content) {
        contentRepository.save(content);
    }

    public DonationSection getContentById(String id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    public DonationSection getLatestContent() {
        return contentRepository.findFirstByOrderByIdAsc(); // Fetch latest content
    }


}
