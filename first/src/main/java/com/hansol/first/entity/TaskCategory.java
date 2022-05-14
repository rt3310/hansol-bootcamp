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
    private Long memberId;

    public TaskCategory(String category, Long taskId, Long memberId) {
        this.category = category;
        this.taskId = taskId;
        this.memberId = memberId;
    }
}
