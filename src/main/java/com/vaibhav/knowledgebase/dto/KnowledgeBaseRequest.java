package com.vaibhav.knowledgebase.dto;

import jakarta.validation.constraints.NotBlank;

public record KnowledgeBaseRequest(
        @NotBlank String topic,
        @NotBlank String explanation,
        String example,
        @NotBlank String category

) {}