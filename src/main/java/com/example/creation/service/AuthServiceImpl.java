package com.example.creation.service;

import com.example.creation.dto.request.AuthREquestDto;
import com.example.creation.dto.request.RegisterRequestDto;
import com.example.creation.dto.response.AuthResponseDto;
import com.example.creation.entity.Role;
import com.example.creation.entity.UserEntity;
import com.example.creation.exception.ResourseAlreadyExistException;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.repository.RoleRepository;
import com.example.creation.repository.UserRepository;
import com.example.creation.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public String register(RegisterRequestDto dto) {
       boolean emailExists=userRepository.findByEmail(dto.email()).isPresent();
       if(emailExists){
           throw new ResourseAlreadyExistException("email already exist");
       }
       Role role=roleRepository.findByRoleName(dto.role()).orElseThrow(() -> new ResoursenotFoundException(
               "role not found"
       ));
       UserEntity user=new UserEntity();
       user.setName(dto.name());
       user.setEmail(dto.email());
       user.setPassword(bCryptPasswordEncoder.encode(dto.password()));
       Role role1=new Role();
       user.setRole(role);
       userRepository.save(user);

        return "User registered successfully";
    }


    @Override
    public AuthResponseDto login(AuthREquestDto authREquestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authREquestDto.email(),
                        authREquestDto.password()
                )
        );
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
