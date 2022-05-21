package com.hansol.first.controller;

import com.hansol.first.dto.*;
import com.hansol.first.entity.TaskCategory;
import com.hansol.first.entity.TaskCompany;
import com.hansol.first.response.Response;
import com.hansol.first.service.ResponseService;
import com.hansol.first.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ResponseService responseService;

    @GetMapping("/tasks")
    public Response<List<OverallTaskDto>> getTasks() {
        return responseService.getResult("tasks", taskService.findAllOverallTask());
    }

    @GetMapping("/task/{id}")
    public Response<OverallTaskDto> getTask(@PathVariable Long id) {
        Optional<OverallTaskDto> overallTask = taskService.findOverallTaskById(id);
        if (overallTask.isEmpty()) {
            return responseService.getFailResult("not exist");
        }
        return responseService.getResult("", overallTask.get());
    }

    @PostMapping("/task")
    public Response<OverallTaskDto> saveTask(@RequestBody @Validated OverallTaskDto overallTaskDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return responseService.getFailResult("valid");
            }
            OverallTaskDto saveResult = taskService.saveOverallTask(overallTaskDto);
            if (saveResult == null) {
                return responseService.getFailResult("member not exist");
            }
            return responseService.getResult("", saveResult);
        } catch (IllegalStateException e) {
            log.info("error: {}", e.getMessage());
            return responseService.getFailResult(e.getMessage());
        }
    }

    @PutMapping("/task/{id}")
    public Response<OverallTaskDto> modifyTask(@PathVariable Long id, @RequestBody @Validated OverallTaskDto overallTaskDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors() || id != overallTaskDto.getId()) {
                return responseService.getFailResult("valid");
            }
            return responseService.getResult("", taskService.modifyOverallTask(overallTaskDto));
        } catch (IllegalStateException e) {
            log.info("error: {}", e.getMessage());
            return responseService.getFailResult(e.getMessage());
        }
    }

    @DeleteMapping("/task/{id}")
    public Response deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteOverallTaskById(id);
            return responseService.getSuccessResult();
        } catch (Exception e) {
            log.info("error: {}", e.getMessage());
            return responseService.getFailResult(e.getMessage());
        }
    }

    @GetMapping("/task/categories")
    public Response<TaskCategory> getTaskCategories() {
        return responseService.getResult("categories", taskService.findAllTaskCategory());
    }

    @GetMapping("/task/category/{id}")
    public Response<TaskCategory> getTaskCategory(@PathVariable Long id) {
        Optional<TaskCategory> taskCategory = taskService.findTaskCategoryById(id);
        if (taskCategory.isEmpty()) {
            return responseService.getFailResult("not exist");
        }
        return responseService.getResult("", taskCategory.get());
    }

    @PostMapping("/task/category")
    public Response<TaskCategory> saveTaskCategory(@RequestBody @Validated TaskCategoryDto taskCategoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.getFailResult("valid");
        }
        return responseService.getResult("", taskService.saveTaskCategory(taskCategoryDto));
    }

    @PutMapping("/task/category/{id}")
    public Response<TaskCategory> modifyTaskCategory(@PathVariable Long id, @RequestBody @Validated TaskCategoryDto taskCategoryDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return responseService.getFailResult("valid");
            }
            return responseService.getResult("", taskService.modifyTaskCategory(id, taskCategoryDto));
        } catch (IllegalStateException e) {
            log.info("error: {}", e.getMessage());
            return responseService.getFailResult(e.getMessage());
        }
    }

    @DeleteMapping("/task/category/{id}")
    public Response deleteTaskCategory(@PathVariable Long id) {
        try {
            taskService.deleteTaskCategoryById(id);
            return responseService.getSuccessResult();
        } catch (Exception e) {
            log.info("error: {}", e.getMessage());
            return responseService.getFailResult("task delete");
        }
    }

    @GetMapping("/task/companies")
    public Response<TaskCompany> getTaskCompanies() {
        return responseService.getResult("companies", taskService.findAllTaskCompany());
    }

    @GetMapping("/task/company/{id}")
    public Response<TaskCompany> getTaskCompany(@PathVariable Long id) {
        Optional<TaskCompany> taskCompany = taskService.findTaskCompanyById(id);
        if (taskCompany.isEmpty()) {
            return responseService.getFailResult("not exist");
        }
        return responseService.getResult("", taskCompany.get());
    }

    @PostMapping("/task/company")
    public Response<TaskCompany> saveTaskCompany(@RequestBody @Validated TaskCompanyDto taskCompanyDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.getFailResult("valid");
        }
        return responseService.getResult("", taskService.saveTaskCompany(taskCompanyDto));
    }

    @PutMapping("/task/company/{id}")
    public Response<TaskCompany> modifyTaskCompany(@PathVariable Long id, @RequestBody @Validated TaskCompanyDto taskCompanyDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return responseService.getFailResult("valid");
            }
            return responseService.getResult("", taskService.modifyTaskCompany(id, taskCompanyDto));
        } catch (IllegalStateException e) {
            log.info("error: {}", e.getMessage());
            return responseService.getFailResult(e.getMessage());
        }
    }

    @DeleteMapping("/task/company/{id}")
    public Response deleteTaskCompany(@PathVariable Long id) {
        try {
            taskService.deleteTaskCompanyById(id);
            return responseService.getSuccessResult();
        } catch (Exception e) {
            log.info("error: {}", e.getMessage());
            return responseService.getFailResult("task delete");
        }
    }
}
