package com.example.creation.service;

import com.example.creation.dto.request.RegisterRequestDto;
import com.example.creation.dto.response.RegesterResponseDto;
import com.example.creation.entity.UserEntity;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.UserMapper;
import com.example.creation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public List<RegesterResponseDto> getAllUser() {

        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    @Override
    public void deleteById(Long id) {
        UserEntity user=userRepository.findById(id).orElseThrow(()->
                new ResoursenotFoundException(
                        "user not found"
                ));
        userRepository.delete(user);

    }
}
