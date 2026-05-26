package com.example.creation.service;

import com.example.creation.dto.request.AuthREquestDto;
import com.example.creation.dto.request.RegisterRequestDto;
import com.example.creation.dto.response.AuthResponseDto;
import com.example.creation.entity.Role;
import com.example.creation.entity.UserEntity;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.repository.UserRepository;
import com.example.creation.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public String register(RegisterRequestDto dto) {
        UserEntity user=new UserEntity();
        user.setName(dto.name());
        user.setEmail(dto.email());

        user.setPassword(bCryptPasswordEncoder.encode(dto.password()));

        user.setRole(Role.USER);

        userRepository.save(user);

        return "User registered successfully";
    }

    private final JwtService jwtService;
    @Override
    public AuthResponseDto login(AuthREquestDto authREquestDto) {
        UserEntity user = userRepository.findByEmail(authREquestDto.email())
                .orElseThrow(() ->
                        new ResoursenotFoundException("Invalid email or password"));

        boolean passwordMatches =bCryptPasswordEncoder.matches(authREquestDto.password(),user.getPassword());

        if (!passwordMatches) {
            throw new ResoursenotFoundException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponseDto(token);
    }
}
