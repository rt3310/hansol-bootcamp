package com.hansol.first.dto;

import com.hansol.first.entity.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class OverallTaskDto {
    private Long id;
    @NotBlank
    private String taskCode;
    @NotEmpty
    private String taskName;
    @NotEmpty
    private String companyName;
    private String category;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private Long memberId;

    public Task toTask() {
        return new Task(taskCode, taskName, memberId);
    }

    public TaskCategory toTaskCategory(Long taskId) {
        return new TaskCategory(category, taskId);
    }

    public TaskCompany toTaskCompany(Long taskId) {
        return new TaskCompany(companyName, taskId);
    }

    public TaskPhone toTaskPhone(Long taskId) {
        return new TaskPhone(phoneNumber, taskId);
    }
}