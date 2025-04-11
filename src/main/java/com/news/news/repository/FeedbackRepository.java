package com.news.news.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.news.entity.Feedback;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
   // Fetch all feedback (for admin panel)
    List<Feedback> findAll();

    // Fetch only approved feedback (for user website)
    List<Feedback> findByApprovedTrue();
}
