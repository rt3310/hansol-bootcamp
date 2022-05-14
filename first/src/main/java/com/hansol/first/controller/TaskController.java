package com.hansol.first.controller;

import com.hansol.first.dto.OverallTaskDto;
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
        return responseService.getResult("", taskService.findOverallTaskById(id));
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
    public String deleteTask(@PathVariable Long id) {
        return "";
    }
}
