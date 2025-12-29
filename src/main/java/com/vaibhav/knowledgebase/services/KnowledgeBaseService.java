package com.vaibhav.knowledgebase.services;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import com.vaibhav.knowledgebase.repository.KnowledgeBaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseService {

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    public KnowledgeBaseService(KnowledgeBaseRepository knowledgeBaseRepository) {
        this.knowledgeBaseRepository = knowledgeBaseRepository;
    }


    public void saveEntry(KnowledgeBase knowledgeBase){
         knowledgeBaseRepository.save(knowledgeBase);
     }

     public List<KnowledgeBase> getAll(){
         return knowledgeBaseRepository.findAll();
     }

     public Optional<KnowledgeBase> findById(Long id){
         return knowledgeBaseRepository.findById(id);
     }

     public void deleteById(Long id){
         knowledgeBaseRepository.deleteById(id);
     }

}
