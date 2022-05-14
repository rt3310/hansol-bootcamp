package com.hansol.first.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
public class Task {
    private Long id;
    private String taskCode;
    private String taskName;
    private Long memberId;

    public Task(String taskCode, String taskName, Long memberId) {
        this.taskCode = taskCode;
        this.taskName = taskName;
        this.memberId = memberId;
    }
}
