package com.hansol.first.service;

import com.hansol.first.dto.OverallTaskDto;

import java.util.List;

public interface TaskService {
    OverallTaskDto saveOverallTask(OverallTaskDto overallTaskDto);
    OverallTaskDto modifyOverallTask(Long taskId, OverallTaskDto overallTaskDto);
    List<OverallTaskDto> findAllOverallTask();
    OverallTaskDto findOverallTaskById(Long id);
    void deleteOverallTaskById(Long id);
}
