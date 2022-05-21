package com.hansol.first.dto;

import com.hansol.first.entity.Task;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class TaskDto {
    @NotBlank
    private Long id;
    @NotBlank
    private String taskCode;
    @NotEmpty
    private String taskName;
    private Boolean memberAssigned;
    private Long memberId;

    public Task toTask() {
        return new Task(taskCode, taskName, memberAssigned, memberId);
    }
}
