package com.hansol.first.dto;

import com.hansol.first.entity.TaskCompany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class TaskCompanyDto {
    private Long id;
    @NotBlank
    private String companyName;
    @NotNull
    private Long taskId;

    public TaskCompanyDto(TaskCompany taskCompany) {
        this.id = taskCompany.getId();
        this.companyName = taskCompany.getCompanyName();
        this.taskId = taskCompany.getTaskId();
    }

    public TaskCompany toTaskCompany() {
        return new TaskCompany(companyName, taskId);
    }
}
