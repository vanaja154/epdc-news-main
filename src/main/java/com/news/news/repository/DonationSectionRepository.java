package com.news.news.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.news.entity.DonationSection;
public interface DonationSectionRepository extends MongoRepository<DonationSection, String> {
    DonationSection findFirstByOrderByIdAsc();
    
}