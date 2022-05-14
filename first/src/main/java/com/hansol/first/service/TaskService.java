package com.hansol.first.service;

import com.hansol.first.dto.*;
import com.hansol.first.entity.Task;
import com.hansol.first.entity.TaskCategory;
import com.hansol.first.entity.TaskCompany;
import com.hansol.first.entity.TaskPhone;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task saveTask(TaskDto taskDto);
    TaskCompany saveTaskCompany(TaskCompanyDto taskCompanyDto);
    TaskCategory saveTaskCategory(TaskCategoryDto taskCategoryDto);
    TaskPhone saveTaskPhone(TaskPhoneDto taskPhoneDto);
    OverallTaskDto saveOverallTask(OverallTaskDto overallTaskDto);

    List<Task> findAllTask();
    List<TaskCompany> findAllTaskCompany();
    List<TaskCategory> findAllTaskCategory();
    List<TaskPhone> findAllTaskPhone();
    List<OverallTaskDto> findAllOverallTask();

    Optional<Task> findTaskById(Long id);
    Optional<TaskCompany> findTaskCompanyById(Long id);
    Optional<TaskCategory> findTaskCategoryById(Long id);
    Optional<TaskPhone> findTaskPhoneById(Long id);
    List<TaskCompany> findTaskCompaniesByTaskId(Long taskId);
    List<TaskCategory> findTaskCategoriesByTaskId(Long taskId);
    List<TaskPhone> findTaskPhonesByTaskId(Long taskId);
    Optional<OverallTaskDto> findOverallTaskById(Long id);

    Task modifyTask(Long id, TaskDto taskDto);
    TaskCompany modifyTaskCompany(Long id, TaskCompanyDto taskCompanyDto);
    TaskCategory modifyTaskCategory(Long id, TaskCategoryDto taskCategoryDto);
    TaskPhone modifyTaskPhone(Long id, TaskPhoneDto taskPhoneDto);
    OverallTaskDto modifyOverallTask(Long taskId, OverallTaskDto overallTaskDto);

    void deleteTaskCompanyById(Long id);
    void deleteTaskCategoryById(Long id);
    void deleteTaskPhoneById(Long id);
    void deleteOverallTaskById(Long id);
}
