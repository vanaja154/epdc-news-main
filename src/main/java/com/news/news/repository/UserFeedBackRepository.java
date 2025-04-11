package com.news.news.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.news.entity.UserFeedBack;

public interface UserFeedBackRepository extends MongoRepository<UserFeedBack, String> {
     List<UserFeedBack> findAll();

     List<UserFeedBack> findByApprovedTrue();
 }