package com.hansol.first.mapper;

import com.hansol.first.dto.OverallTaskDto;
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
    void updateOverallTask(OverallTaskDto overallTaskDto);
    List<OverallTaskDto> findAllOverallTask();
    OverallTaskDto findOverallTaskById(Long id);
    void deleteTaskById(Long id);
    void deleteTaskCompanyByTaskId(Long taskId);
    void deleteTaskCategoryByTaskId(Long taskId);
    void deleteTaskPhoneByTaskId(Long taskId);
    void deleteTaskCompanyByMemberId(Long memberId);
    void deleteTaskCategoryByMemberId(Long memberId);
    void deleteTaskPhoneByMemberId(Long memberId);
}