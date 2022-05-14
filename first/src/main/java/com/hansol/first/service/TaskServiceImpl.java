package com.hansol.first.service;

import com.hansol.first.dto.MemberDto;
import com.hansol.first.dto.OverallTaskDto;
import com.hansol.first.entity.*;
import com.hansol.first.mapper.MemberMapper;
import com.hansol.first.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final MemberMapper memberMapper;

    @Override
    public OverallTaskDto saveOverallTask(OverallTaskDto overallTaskDto) {
        Task task = overallTaskDto.toTask();
        Member member = overallTaskDto.toMember();
        taskMapper.saveTask(task);
        memberMapper.save(member);
        Long taskId = task.getId();
        Long memberId = member.getId();
        taskMapper.saveTaskCompany(overallTaskDto.toTaskCompany(taskId, memberId));
        taskMapper.saveTaskCategory(overallTaskDto.toTaskCategory(taskId, memberId));
        taskMapper.saveTaskPhone(overallTaskDto.toTaskPhone(taskId, memberId));
        overallTaskDto.setId(taskId);
        return overallTaskDto;
    }

    @Override
    public OverallTaskDto modifyOverallTask(Long taskId, OverallTaskDto overallTaskDto) {
        OverallTaskDto overallTask = taskMapper.findOverallTaskById(taskId);
        overallTask.setTaskCode(overallTaskDto.getTaskCode());
        overallTask.setTaskName(overallTaskDto.getTaskName());
        overallTask.setMemberName(overallTaskDto.getMemberName());
        overallTask.setMemberRank(overallTaskDto.getMemberRank());
        overallTask.setCategory(overallTaskDto.getCategory());
        overallTask.setCompanyName(overallTaskDto.getCompanyName());
        overallTask.setPhoneNumber(overallTaskDto.getPhoneNumber());
        taskMapper.updateOverallTask(overallTask);
        return overallTaskDto;
    }

    @Override
    public List<OverallTaskDto> findAllOverallTask() {
        return taskMapper.findAllOverallTask();
    }

    @Override
    public OverallTaskDto findOverallTaskById(Long id) {
        return taskMapper.findOverallTaskById(id);
    }

    @Override
    public void deleteOverallTaskById(Long id) {
        taskMapper.deleteTaskCategoryByTaskId(id);
        taskMapper.deleteTaskPhoneByTaskId(id);
        taskMapper.deleteTaskCompanyByTaskId(id);
        taskMapper.deleteTaskById(id);
    }
}
