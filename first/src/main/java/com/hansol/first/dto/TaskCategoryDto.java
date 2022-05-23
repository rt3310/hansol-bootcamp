package com.hansol.first.dto;

import com.hansol.first.entity.TaskCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class TaskCategoryDto {
    private Long id;
    @NotNull
    private String category;
    private Long taskId;

    public TaskCategoryDto(TaskCategory taskCategory) {
        this.id = taskCategory.getId();
        this.category = taskCategory.getCategory();
        this.taskId = taskCategory.getTaskId();
    }

    public TaskCategory toTaskCategory() {
        return new TaskCategory(category, taskId);
    }
}
