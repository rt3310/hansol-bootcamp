package com.hansol.first.mapper;

import com.hansol.first.dto.OverallTaskDto;
import com.hansol.first.dto.TaskDto;
import com.hansol.first.entity.Task;
import com.hansol.first.entity.TaskCategory;
import com.hansol.first.entity.TaskCompany;
import com.hansol.first.entity.TaskPhone;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {

    Long saveTask(Task task);
    Long saveTaskCompany(TaskCompany taskCompany);
    Long saveTaskCategory(TaskCategory taskCategory);
    Long saveTaskPhone(TaskPhone taskPhone);

    List<Task> findAllTask();
    List<TaskCompany> findAllTaskCompany();
    List<TaskCategory> findAllTaskCategory();
    List<TaskPhone> findAllTaskPhone();
    List<OverallTaskDto> findAllOverallTask();

    void updateTask(Task task);
    void updateTaskCompany(TaskCompany taskCompany);
    void updateTaskCategory(TaskCategory taskCategory);
    void updateTaskPhone(TaskPhone taskPhone);
    void updateOverallTask(OverallTaskDto overallTaskDto);

    void deleteTaskById(Long id);
    void deleteTaskCompanyByTaskId(Long taskId);
    void deleteTaskCategoryByTaskId(Long taskId);
    void deleteTaskPhoneByTaskId(Long taskId);
}