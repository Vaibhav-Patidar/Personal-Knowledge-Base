package com.vaibhav.knowledgebase.services;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import com.vaibhav.knowledgebase.repository.KnowledgeBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KnowledgeBaseService {

    @Autowired
    private KnowledgeBaseRepository knowledgeBaseRepository;

     public void saveEntry(KnowledgeBase knowledgeBase){
         knowledgeBaseRepository.save(knowledgeBase);
     }

     public List<KnowledgeBase> getAll(){
         return knowledgeBaseRepository.findAll();
     }

}
