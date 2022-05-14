package com.hansol.first.dto;

import com.hansol.first.entity.TaskCompany;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class TaskCompanyDto {
    @NotBlank
    private String companyName;
    @NotNull
    private Long taskId;

    public TaskCompany toTaskCompany() {
        return new TaskCompany(companyName, taskId);
    }
}
