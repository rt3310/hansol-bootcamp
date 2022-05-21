package com.hansol.first.service;

import com.hansol.first.dto.*;
import com.hansol.first.entity.*;
import com.hansol.first.mapper.MemberMapper;
import com.hansol.first.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final MemberMapper memberMapper;

    @Override
    public Task saveTask(TaskDto taskDto) {
        Task task = taskDto.toTask();
        taskMapper.saveTask(task);
        return task;
    }

    @Override
    public TaskCompany saveTaskCompany(TaskCompanyDto taskCompanyDto) {
        TaskCompany taskCompany = taskCompanyDto.toTaskCompany();
        taskMapper.saveTaskCompany(taskCompany);
        return taskCompany;
    }

    @Override
    public TaskCategory saveTaskCategory(TaskCategoryDto taskCategoryDto) {
        TaskCategory taskCategory = taskCategoryDto.toTaskCategory();
        taskMapper.saveTaskCategory(taskCategory);
        return taskCategory;
    }

    @Override
    public OverallTaskDto saveOverallTask(OverallTaskDto overallTaskDto) {
        Member member = memberMapper.findById(overallTaskDto.getMemberId());
        Task task = overallTaskDto.toTask();
        taskMapper.saveTask(task);
        if (member != null) {
            memberMapper.save(member);
        }

        Long taskId = task.getId();
        overallTaskDto.toTaskCompanies().stream()
                .forEach(c -> taskMapper.saveTaskCompany(c));
        overallTaskDto.toTaskCategories().stream()
                .forEach(c -> taskMapper.saveTaskCategory(c));

        overallTaskDto.setId(taskId);
        return overallTaskDto;
    }

    @Override
    public List<Task> findAllTask() {
        return taskMapper.findAllTask();
    }

    @Override
    public List<TaskCompany> findAllTaskCompany() {
        return taskMapper.findAllTaskCompany();
    }

    @Override
    public List<TaskCategory> findAllTaskCategory() {
        return taskMapper.findAllTaskCategory();
    }

    @Override
    public List<OverallTaskDto> findAllOverallTask() {
        return taskMapper.findAllTask().stream()
                .map(t -> {
                    List<TaskCompanyDto> taskCompanyDtos = findTaskCompaniesByTaskId(t.getId()).stream()
                            .map(c -> new TaskCompanyDto(c))
                            .collect(Collectors.toList());
                    List<TaskCategoryDto> taskCategoryDtos = findTaskCategoriesByTaskId(t.getId()).stream()
                            .map(c -> new TaskCategoryDto(c))
                            .collect(Collectors.toList());
                    return new OverallTaskDto(t, taskCompanyDtos, taskCategoryDtos, t.getMemberAssigned(), t.getMemberId());
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Task> findTaskById(Long id) {
        return findAllTask().stream()
                .filter(t -> t.getId() == id)
                .findAny();
    }

    @Override
    public Optional<TaskCompany> findTaskCompanyById(Long id) {
        return findAllTaskCompany().stream()
                .filter(c -> c.getId() == id)
                .findAny();
    }

    @Override
    public List<TaskCompany> findTaskCompaniesByTaskId(Long taskId) {
        return findAllTaskCompany().stream()
                .filter(c -> c.getTaskId() == taskId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskCategory> findTaskCategoryById(Long id) {
        return findAllTaskCategory().stream()
                .filter(c -> c.getId() == id)
                .findAny();
    }

    @Override
    public List<TaskCategory> findTaskCategoriesByTaskId(Long taskId) {
        return findAllTaskCategory().stream()
                .filter(c -> c.getTaskId() == taskId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OverallTaskDto> findOverallTaskById(Long id) {
        return findAllOverallTask().stream()
                .filter(m -> m.getId() == id)
                .findAny();
    }

    @Override
    public Task modifyTask(Long id, TaskDto taskDto) {
        Optional<Task> wrappedTask = findTaskById(id);
        if (wrappedTask.isEmpty()) {
            return null;
        }
        Task task = wrappedTask.get();
        task.setTaskCode(taskDto.getTaskCode());
        task.setTaskName(taskDto.getTaskName());
        task.setMemberAssigned(taskDto.getMemberAssigned());
        task.setMemberId(taskDto.getMemberId());
        taskMapper.updateTask(task);
        return task;
    }

    @Override
    public TaskCompany modifyTaskCompany(Long id, TaskCompanyDto taskCompanyDto) {
        Optional<TaskCompany> wrappedTaskCompany = findTaskCompanyById(id);
        if (wrappedTaskCompany.isEmpty()) {
            return null;
        }
        TaskCompany taskCompany = wrappedTaskCompany.get();
        taskCompany.setCompanyName(taskCompanyDto.getCompanyName());
        taskCompany.setTaskId(taskCompanyDto.getTaskId());
        taskMapper.updateTaskCompany(taskCompany);
        return taskCompany;
    }

    @Override
    public TaskCategory modifyTaskCategory(Long id, TaskCategoryDto taskCategoryDto) {
        Optional<TaskCategory> wrappedTaskCategory = findTaskCategoryById(id);
        if (wrappedTaskCategory.isEmpty()) {
            return null;
        }
        TaskCategory taskCategory = wrappedTaskCategory.get();
        taskCategory.setCategory(taskCategoryDto.getCategory());
        taskCategory.setTaskId(taskCategoryDto.getTaskId());
        taskMapper.updateTaskCategory(taskCategory);
        return taskCategory;
    }

    @Override
    public OverallTaskDto modifyOverallTask(OverallTaskDto overallTaskDto) {
        Optional<Task> wrappedTask = findTaskById(overallTaskDto.getId());
        Optional<Member> member = Optional.ofNullable(memberMapper.findById(overallTaskDto.getMemberId()));

        if (wrappedTask.isEmpty()) {
            return null;
        }

        Task task = wrappedTask.get();
        task.setTaskCode(overallTaskDto.getTaskCode());
        task.setTaskName(overallTaskDto.getTaskName());
        task.setMemberAssigned(member.isPresent());
        task.setMemberId(member.orElse(null).getId());
        taskMapper.updateTask(task);

        overallTaskDto.getCompanies().stream()
                .forEach(c -> modifyTaskCompany(c.getId(), c));
        overallTaskDto.getCategories().stream()
                .forEach(c -> modifyTaskCategory(c.getId(), c));

        return overallTaskDto;
    }

    @Override
    public void deleteTaskCompanyById(Long id) {
        if (findTaskCompanyById(id).isEmpty()) {
            throw new IllegalStateException();
        }
        taskMapper.deleteTaskCategoryByTaskId(id);
    }

    @Override
    public void deleteTaskCategoryById(Long id) {
        if (findTaskCategoryById(id).isEmpty()) {
            throw new IllegalStateException();
        }
        taskMapper.deleteTaskCategoryByTaskId(id);
    }

    @Override
    public void deleteOverallTaskById(Long id) {
        if (findOverallTaskById(id).isEmpty()) {
            throw new IllegalStateException();
        }
        taskMapper.deleteTaskCategoryByTaskId(id);
        taskMapper.deleteTaskCompanyByTaskId(id);
        taskMapper.deleteTaskById(id);
    }
}
