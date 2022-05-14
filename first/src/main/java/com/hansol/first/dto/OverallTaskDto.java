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
    @NotBlank
    private String memberName;
    @NotBlank
    private String memberRank;
    @NotEmpty
    private String companyName;
    private String category;
    @NotBlank
    private String phoneNumber;

    public Task toTask() {
        return new Task(taskCode, taskName);
    }

    public Member toMember() {
        return new Member(memberName, memberRank);
    }

    public TaskCategory toTaskCategory(Long taskId, Long memberId) {
        return new TaskCategory(category, taskId, memberId);
    }

    public TaskCompany toTaskCompany(Long taskId, Long memberId) {
        return new TaskCompany(companyName, taskId, memberId);
    }

    public TaskPhone toTaskPhone(Long taskId, Long memberId) {
        return new TaskPhone(phoneNumber, taskId, memberId);
    }
}