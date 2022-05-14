package com.hansol.first.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TaskCompany {
    private Long id;
    private String companyName;
    private Long taskId;

    public TaskCompany(String companyName, Long taskId) {
        this.companyName = companyName;
        this.taskId = taskId;
    }
}
