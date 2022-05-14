package com.hansol.first.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TaskPhone {
    private Long id;
    private String phoneNumber;
    private Long taskId;

    public TaskPhone(String phoneNumber, Long taskId) {
        this.phoneNumber = phoneNumber;
        this.taskId = taskId;
    }
}
