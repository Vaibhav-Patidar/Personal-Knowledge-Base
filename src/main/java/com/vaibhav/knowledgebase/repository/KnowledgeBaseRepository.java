package com.vaibhav.knowledgebase.repository;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KnowledgeBaseRepository extends MongoRepository<KnowledgeBase, ObjectId> {
    List<KnowledgeBase> findByCategory(String category);

    List<KnowledgeBase> findByTopicContainingIgnoreCase(String keyword);
}
