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
    private Long memberId;

    public TaskPhone(String phoneNumber, Long taskId, Long memberId) {
        this.phoneNumber = phoneNumber;
        this.taskId = taskId;
        this.memberId = memberId;
    }
}
