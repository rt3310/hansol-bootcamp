package com.hansol.first.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TaskCategory {
    private Long id;
    private String category;
    private Long taskId;

    public TaskCategory(String category, Long taskId) {
        this.category = category;
        this.taskId = taskId;
    }
}