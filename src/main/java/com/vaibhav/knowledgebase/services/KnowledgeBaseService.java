package com.vaibhav.knowledgebase.services;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import com.vaibhav.knowledgebase.repository.KnowledgeBaseRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseService {

    @Autowired
    private KnowledgeBaseRepository knowledgeBaseRepository;

     public void saveEntry(KnowledgeBase knowledgeBase){
         knowledgeBaseRepository.save(knowledgeBase);
     }

     public List<KnowledgeBase> getAll(){
         return knowledgeBaseRepository.findAll();
     }

     public Optional<KnowledgeBase> findById(ObjectId id){
         return knowledgeBaseRepository.findById(id);
     }

     public void deleteById(ObjectId id){
         knowledgeBaseRepository.deleteById(id);
     }

}
