package com.bentoo.taskman.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private LocalDateTime startedAt;
    private LocalDateTime endAt;
    private UUID userId;
}
