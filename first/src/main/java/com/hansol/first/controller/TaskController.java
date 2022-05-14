package com.hansol.first.controller;

import com.hansol.first.dto.*;
import com.hansol.first.entity.TaskCategory;
import com.hansol.first.entity.TaskCompany;
import com.hansol.first.entity.TaskPhone;
import com.hansol.first.response.Response;
import com.hansol.first.service.ResponseService;
import com.hansol.first.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return responseService.getResult("", taskService.findOverallTaskById(id).get());
    }

    @PostMapping("/task")
    public Response<OverallTaskDto> saveTask(@RequestBody @Validated OverallTaskDto overallTaskDto) {
        return responseService.getResult("", taskService.saveOverallTask(overallTaskDto));
    }

    @PutMapping("/task/{id}")
    public Response<OverallTaskDto> modifyTask(@PathVariable Long id, @RequestBody @Validated OverallTaskDto overallTaskDto) {
        return responseService.getResult("", taskService.modifyOverallTask(id, overallTaskDto));
    }

    @DeleteMapping("/task/{id}")
    public Response deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteOverallTaskById(id);
        } catch (Exception e) {
            return responseService.getFailResult("task delete");
        }
        return responseService.getSuccessResult();
    }

    @GetMapping("/task/categories")
    public Response<TaskCategory> getTaskCategories() {
        return responseService.getResult("category", taskService.findAllTaskCategory());
    }

    @GetMapping("/task/category/{id}")
    public Response<TaskCategory> getTaskCategory(@PathVariable Long id) {
        return responseService.getResult("", taskService.findTaskCategoryById(id));
    }

    @PostMapping("/task/category")
    public Response<TaskCategory> saveTaskCategory(@RequestBody @Validated TaskCategoryDto taskCategoryDto) {
        return responseService.getResult("", taskService.saveTaskCategory(taskCategoryDto));
    }

    @PutMapping("/task/category/{id}")
    public Response<TaskCategory> modifyTaskCategory(@PathVariable Long id, @RequestBody @Validated TaskCategoryDto taskCategoryDto) {
        return responseService.getResult("", taskService.modifyTaskCategory(id, taskCategoryDto));
    }

    @DeleteMapping("/task/category/{id}")
    public Response deleteTaskCategory(@PathVariable Long id) {
        try {
            taskService.deleteTaskCategoryById(id);
        } catch (Exception e) {
            return responseService.getFailResult("task delete");
        }
        return responseService.getSuccessResult();
    }

    @GetMapping("/task/companies")
    public Response<TaskCompany> getTaskCompanies() {
        return responseService.getResult("company", taskService.findAllTaskCompany());
    }

    @GetMapping("/task/company/{id}")
    public Response<TaskCompany> getTaskCompany(@PathVariable Long id) {
        return responseService.getResult("", taskService.findTaskCompanyById(id));
    }

    @PostMapping("/task/company")
    public Response<TaskCompany> saveTaskCompany(@RequestBody @Validated TaskCompanyDto taskCompanyDto) {
        return responseService.getResult("", taskService.saveTaskCompany(taskCompanyDto));
    }

    @PutMapping("/task/company/{id}")
    public Response<TaskCompany> modifyTaskCompany(@PathVariable Long id, @RequestBody @Validated TaskCompanyDto taskCompanyDto) {
        return responseService.getResult("", taskService.modifyTaskCompany(id, taskCompanyDto));
    }

    @DeleteMapping("/task/company/{id}")
    public Response deleteTaskCompany(@PathVariable Long id) {
        try {
            taskService.deleteTaskCompanyById(id);
        } catch (Exception e) {
            return responseService.getFailResult("task delete");
        }
        return responseService.getSuccessResult();
    }

    @GetMapping("/task/phones")
    public Response<TaskPhone> getTaskPhones() {
        return responseService.getResult("category", taskService.findAllTaskPhone());
    }

    @GetMapping("/task/phone/{id}")
    public Response<TaskPhone> getTaskPhone(@PathVariable Long id) {
        return responseService.getResult("", taskService.findTaskPhoneById(id));
    }

    @PostMapping("/task/phone")
    public Response<TaskPhone> saveTaskPhone(@RequestBody @Validated TaskPhoneDto taskPhoneDto) {
        return responseService.getResult("", taskService.saveTaskPhone(taskPhoneDto));
    }

    @PutMapping("/task/phone/{id}")
    public Response<TaskPhone> modifyTaskPhone(@PathVariable Long id, @RequestBody @Validated TaskPhoneDto taskPhoneDto) {
        return responseService.getResult("", taskService.modifyTaskPhone(id, taskPhoneDto));
    }

    @DeleteMapping("/task/phone/{id}")
    public Response deleteTaskPhone(@PathVariable Long id) {
        try {
            taskService.deleteTaskPhoneById(id);
        } catch (Exception e) {
            return responseService.getFailResult("task delete");
        }
        return responseService.getSuccessResult();
    }
}
