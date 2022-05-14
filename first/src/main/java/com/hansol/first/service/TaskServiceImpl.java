package com.hansol.first.service;

import com.hansol.first.dto.*;
import com.hansol.first.entity.*;
import com.hansol.first.mapper.MemberMapper;
import com.hansol.first.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public TaskPhone saveTaskPhone(TaskPhoneDto taskPhoneDto) {
        TaskPhone taskPhone = taskPhoneDto.toTaskPhone();
        taskMapper.saveTaskPhone(taskPhone);
        return taskPhone;
    }

    @Override
    public OverallTaskDto saveOverallTask(OverallTaskDto overallTaskDto) {
        Member member = memberMapper.findById(overallTaskDto.getMemberId());
        if (member == null) {
            return null;
        }
        Task task = overallTaskDto.toTask();
        taskMapper.saveTask(task);
        memberMapper.save(member);

        Long taskId = task.getId();
        taskMapper.saveTaskCompany(overallTaskDto.toTaskCompany(taskId));
        taskMapper.saveTaskCategory(overallTaskDto.toTaskCategory(taskId));
        taskMapper.saveTaskPhone(overallTaskDto.toTaskPhone(taskId));
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
    public List<TaskPhone> findAllTaskPhone() {
        return taskMapper.findAllTaskPhone();
    }

    @Override
    public List<OverallTaskDto> findAllOverallTask() {
        return taskMapper.findAllOverallTask();
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
    public Optional<TaskCategory> findTaskCategoryById(Long id) {
        return findAllTaskCategory().stream()
                .filter(c -> c.getId() == id)
                .findAny();
    }

    @Override
    public Optional<TaskPhone> findTaskPhoneById(Long id) {
        return findAllTaskPhone().stream()
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
    public List<TaskCategory> findTaskCategoriesByTaskId(Long taskId) {
        return findAllTaskCategory().stream()
                .filter(c -> c.getTaskId() == taskId)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskPhone> findTaskPhonesByTaskId(Long taskId) {
        return findAllTaskPhone().stream()
                .filter(p -> p.getTaskId() == taskId)
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
    public TaskPhone modifyTaskPhone(Long id, TaskPhoneDto taskPhoneDto) {
        Optional<TaskPhone> wrappedTaskPhone = findTaskPhoneById(id);
        if (wrappedTaskPhone.isEmpty()) {
            return null;
        }
        TaskPhone taskPhone = wrappedTaskPhone.get();
        taskPhone.setPhoneNumber(taskPhoneDto.getPhoneNumber());
        taskPhone.setTaskId(taskPhoneDto.getTaskId());
        taskMapper.updateTaskPhone(taskPhone);
        return taskPhone;
    }

    @Override
    public OverallTaskDto modifyOverallTask(Long taskId, OverallTaskDto overallTaskDto) {
        if (findOverallTaskById(taskId).isEmpty()) {
            return null;
        }
        OverallTaskDto overallTask = findOverallTaskById(taskId).get();
        overallTask.setTaskCode(overallTaskDto.getTaskCode());
        overallTask.setTaskName(overallTaskDto.getTaskName());
        Member member = memberMapper.findById(overallTaskDto.getMemberId());
        if (member == null) {
            return null;
        }
        overallTask.setMemberId(member.getId());
        overallTask.setCategory(overallTaskDto.getCategory());
        overallTask.setCompanyName(overallTaskDto.getCompanyName());
        overallTask.setPhoneNumber(overallTaskDto.getPhoneNumber());
        taskMapper.updateOverallTask(overallTask);
        overallTaskDto.setId(taskId);
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
    public void deleteTaskPhoneById(Long id) {
        if (findTaskPhoneById(id).isEmpty()) {
            throw new IllegalStateException();
        }
        taskMapper.deleteTaskPhoneByTaskId(id);
    }

    @Override
    public void deleteOverallTaskById(Long id) {
        if (findOverallTaskById(id).isEmpty()) {
            throw new IllegalStateException();
        }
        taskMapper.deleteTaskCategoryByTaskId(id);
        taskMapper.deleteTaskPhoneByTaskId(id);
        taskMapper.deleteTaskCompanyByTaskId(id);
        taskMapper.deleteTaskById(id);
    }
}
