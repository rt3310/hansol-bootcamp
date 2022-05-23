package com.hansol.first.dto;

import com.hansol.first.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class OverallTaskDto {
    private Long id;
    @NotNull
    private String taskCode;
    @NotNull
    private String taskName;
    private List<TaskCompanyDto> companies;
    private List<TaskCategoryDto> categories;
    private Boolean memberAssigned;
    private Long memberId;

    public OverallTaskDto(Task task, List<TaskCompanyDto> companies, List<TaskCategoryDto> categories, Boolean memberAssigned, Long memberId) {
        this.id = task.getId();
        this.taskCode = task.getTaskCode();
        this.taskName = task.getTaskName();
        this.companies = companies;
        this.categories = categories;
        this.memberAssigned = memberAssigned;
        this.memberId = memberId;
    }

    public Task toTask() {
        return new Task(taskCode, taskName, memberAssigned, memberId);
    }

    public List<TaskCategory> toTaskCategories() {
        return categories.stream()
                .map(c -> c.toTaskCategory())
                .collect(Collectors.toList());
    }

    public List<TaskCompany> toTaskCompanies() {
        return companies.stream()
                .map(c -> c.toTaskCompany())
                .collect(Collectors.toList());
    }
}