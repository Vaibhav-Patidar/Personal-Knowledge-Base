package com.vaibhav.knowledgebase.controller;

import com.vaibhav.knowledgebase.entity.KnowledgeBase;
import com.vaibhav.knowledgebase.services.KnowledgeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/base")
public class KnowledgeBaseController {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @GetMapping
    public List<KnowledgeBase> getAll(){
        return knowledgeBaseService.getAll();
    }

    @PostMapping
    public KnowledgeBase createEntry(@RequestBody KnowledgeBase myEntry){
        myEntry.setCreatedAt(LocalDateTime.now());
        knowledgeBaseService.saveEntry(myEntry);
        return myEntry;
    }
}
