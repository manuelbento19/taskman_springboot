package com.bentoo.taskman.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TaskDTO {
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private UUID userId;
}
