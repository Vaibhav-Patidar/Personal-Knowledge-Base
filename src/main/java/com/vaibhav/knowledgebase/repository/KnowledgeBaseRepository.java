package com.vaibhav.knowledgebase.repository;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Long> {
    List<KnowledgeBase> findByCategory(String category);
    List<KnowledgeBase> findByUser_UserId(Long userId);
    List<KnowledgeBase> findByTopicContainingIgnoreCase(String keyword);
}
