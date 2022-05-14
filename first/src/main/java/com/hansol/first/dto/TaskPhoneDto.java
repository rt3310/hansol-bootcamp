package com.hansol.first.dto;

import com.hansol.first.entity.TaskPhone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class TaskPhoneDto {
    @NotBlank
    private String phoneNumber;
    @NotNull
    private Long taskId;

    public TaskPhone toTaskPhone() {
        return new TaskPhone(phoneNumber, taskId);
    }
}
