package com.hansol.first.mapper;

import com.hansol.first.dto.OverallTaskDto;
import com.hansol.first.entity.Task;
import com.hansol.first.entity.TaskCategory;
import com.hansol.first.entity.TaskCompany;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {

    Long saveTask(Task task);
    Long saveTaskCompany(TaskCompany taskCompany);
    Long saveTaskCategory(TaskCategory taskCategory);

    List<Task> findAllTask();
    List<TaskCompany> findAllTaskCompany();
    List<TaskCategory> findAllTaskCategory();

    void updateTask(Task task);
    void updateTaskCompany(TaskCompany taskCompany);
    void updateTaskCategory(TaskCategory taskCategory);
    void updateTaskMemberToNull(Long memberId);

    void deleteTaskById(Long id);
    void deleteTaskCompanyById(Long id);
    void deleteTaskCompanyByTaskId(Long taskId);
    void deleteTaskCategoryById(Long id);
    void deleteTaskCategoryByTaskId(Long taskId);
}