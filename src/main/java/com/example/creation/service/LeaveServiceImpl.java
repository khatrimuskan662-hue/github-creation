package com.example.creation.service;

import com.example.creation.dto.request.LeaveRequestDto;
import com.example.creation.dto.response.LeaveResponseDto;
import com.example.creation.entity.LeaveEntity;
import com.example.creation.entity.LeaveStatus;
import com.example.creation.entity.TeacherEntity;
import com.example.creation.exception.ResourceNotFoundException;
import com.example.creation.mapper.LeaveMapper;
import com.example.creation.repository.LeaveRepository;
import com.example.creation.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements Leaveservice{
    private final TeacherRepository teacherRepository;
    private final LeaveRepository leaveRepository;
    private final LeaveMapper leaveMapper;
    @Override
    public LeaveResponseDto applyLeave(LeaveRequestDto dto) {
        TeacherEntity teacher=teacherRepository.findById(dto.teacherId()).orElseThrow(
                ()-> new ResourceNotFoundException("teacher not found")
        );
        LeaveEntity leave=new LeaveEntity();
        leave.setTeacher(teacher);
        leave.setFromDate(dto.fromDate());
        leave.setToDate(dto.toDate());
        leave.setLeaveType(dto.leaveType());
        leave.setReason(dto.reason());

        leave.setStatus(LeaveStatus.valueOf("PENDING"));
        LeaveEntity saved=leaveRepository.save(leave);

        return leaveMapper.toResponseDto(saved);
    }

    @Override
    public List<LeaveResponseDto> getAllLeaves() {
        return leaveRepository.findAll().stream().map(
                leaveMapper::toResponseDto
        ).toList();
    }

    @Override
    public LeaveResponseDto approveLeave(Integer leaveId) {
        LeaveEntity leave=leaveRepository.findById(leaveId).orElseThrow(
                ()-> new ResourceNotFoundException("leave not found")
        );
        leave.setStatus(LeaveStatus.valueOf("APPROVED"));
        return leaveMapper.toResponseDto(leaveRepository.save(leave));
    }

    @Override
    public LeaveResponseDto rejectLeave(Integer leaveId) {
        LeaveEntity leave=leaveRepository.findById(leaveId).orElseThrow(
                ()->new ResourceNotFoundException("leave not found")
        );
        leave.setStatus(LeaveStatus.valueOf("REJECTED"));
        return leaveMapper.toResponseDto(leaveRepository.save(leave));
    }
}
