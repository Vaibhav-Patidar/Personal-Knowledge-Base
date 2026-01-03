package com.vaibhav.knowledgebase.services;

import com.vaibhav.knowledgebase.dto.KnowledgeBaseRequest;
import com.vaibhav.knowledgebase.dto.KnowledgeBaseResponse;
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

    public KnowledgeBaseResponse createEntry(KnowledgeBaseRequest request, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        KnowledgeBase kb = mapToEntity(request);
        kb.setUser(user);
        KnowledgeBase saved = knowledgeBaseRepository.save(kb);
        return mapToResponse(saved);
    }

    public List<KnowledgeBaseResponse> getByUser(long userId) {
        return knowledgeBaseRepository.findByUser_UserId(userId).stream().map(this::mapToResponse).toList();
    }


    public void deleteById(Long id, long userId) {
        KnowledgeBase kb = knowledgeBaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        if (!kb.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        knowledgeBaseRepository.deleteById(id);
    }

    public KnowledgeBaseResponse updateEntry(Long entryId, long userId, KnowledgeBaseRequest request) {

        // check existence
        KnowledgeBase existing = knowledgeBaseRepository.findById(entryId).orElseThrow(() -> new RuntimeException("Entry not found"));
        // ownership validation
        if (!existing.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        existing.setTopic(request.topic());
        existing.setExplanation(request.explanation());
        existing.setExample(request.example());
        existing.setCategory(request.category());
        existing.setUpdatedAt(LocalDateTime.now());

        KnowledgeBase saved = knowledgeBaseRepository.save(existing);
        return mapToResponse(saved);

    }

    private KnowledgeBase mapToEntity(KnowledgeBaseRequest request) {
        KnowledgeBase kb = new KnowledgeBase();
        kb.setTopic(request.topic());
        kb.setExplanation(request.explanation());
        kb.setExample(request.example());
        kb.setCategory(request.category());
        return kb;
    }

    private KnowledgeBaseResponse mapToResponse(KnowledgeBase kb) {
        return new KnowledgeBaseResponse(
                kb.getId(),
                kb.getTopic(),
                kb.getExplanation(),
                kb.getExample(),
                kb.getCategory(),
                kb.getCreatedAt(),
                kb.getUpdatedAt()
        );
    }
}
