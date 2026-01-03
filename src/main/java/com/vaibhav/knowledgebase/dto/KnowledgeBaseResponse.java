package com.vaibhav.knowledgebase.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record KnowledgeBaseResponse(
        Long id,
        String topic,
        String explanation,
        String example,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

