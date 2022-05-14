package com.hansol.first.dto;

import com.hansol.first.entity.TaskCategory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class TaskCategoryDto {
    @NotBlank
    private String category;
    @NotNull
    private Long taskId;

    public TaskCategory toTaskCategory() {
        return new TaskCategory(category, taskId);
    }
}
