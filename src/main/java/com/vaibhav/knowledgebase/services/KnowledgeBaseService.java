package com.vaibhav.knowledgebase.services;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import com.vaibhav.knowledgebase.entity.User;
import com.vaibhav.knowledgebase.repository.KnowledgeBaseRepository;
import com.vaibhav.knowledgebase.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseService {

    private final KnowledgeBaseRepository knowledgeBaseRepository;
    private final UserRepository userRepository;


    public KnowledgeBaseService(KnowledgeBaseRepository knowledgeBaseRepository, UserRepository userRepository) {
        this.knowledgeBaseRepository = knowledgeBaseRepository;
        this.userRepository = userRepository;
    }

    public void saveEntry(KnowledgeBase knowledgeBase, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        knowledgeBase.setUser(user);
        knowledgeBaseRepository.save(knowledgeBase);
    }

    public List<KnowledgeBase> getByUser(long userId) {
        return knowledgeBaseRepository.findByUser_UserId(userId);
    }


    public void deleteById(Long id, long userId) {
        KnowledgeBase kb = knowledgeBaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        if (!kb.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        knowledgeBaseRepository.deleteById(id);
    }

    public KnowledgeBase updateEntry(Long entryId, long userId, KnowledgeBase updatedData) {
        KnowledgeBase existing = knowledgeBaseRepository.findById(entryId).orElseThrow(() -> new RuntimeException("Entry not found"));

        // ownership validation
        if (!existing.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        if (updatedData.getTopic() != null && !updatedData.getTopic().isBlank()) {
            existing.setTopic(updatedData.getTopic());
        }
        if (updatedData.getExplanation() != null && !updatedData.getExplanation().isBlank()) {
            existing.setExplanation(updatedData.getExplanation());
        }
        if (updatedData.getExample() != null) {
            existing.setExample(updatedData.getExample());
        }
        if (updatedData.getCategory() != null && !updatedData.getCategory().isBlank()) {
            existing.setCategory(updatedData.getCategory());
        }
        existing.setUpdatedAt(LocalDateTime.now());

        return knowledgeBaseRepository.save(existing);
    }


}
