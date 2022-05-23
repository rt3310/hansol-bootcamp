package com.hansol.first.dto;

import com.hansol.first.entity.Task;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class TaskDto {
    private Long id;
    @NotNull
    private String taskCode;
    @NotNull
    private String taskName;
    private Boolean memberAssigned;
    private Long memberId;

    public Task toTask() {
        return new Task(taskCode, taskName, memberAssigned, memberId);
    }
}
