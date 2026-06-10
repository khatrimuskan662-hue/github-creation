package com.example.creation.controller;

import com.example.creation.dto.request.CalenderRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.CalenderResponseDto;
import com.example.creation.service.CalenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class CalenderController {
    private final CalenderService calenderService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CalenderResponseDto>> createEvent
            (@Valid @RequestBody CalenderRequestDto dto){
        CalenderResponseDto event=calenderService.createEvent(dto);
        ApiResponse<CalenderResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Faculty created successfully",
                        event
                );

        return ResponseEntity.ok(response);
    }
@GetMapping
@PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<ApiResponse<List<CalenderResponseDto>>> getAllEvents(){
        List<CalenderResponseDto> allEvent=calenderService.getAllEvents();
        ApiResponse<List<CalenderResponseDto>> response=
                new ApiResponse<>(
                        true,
                        "fetched all events",
                        allEvent
                );
        return ResponseEntity.ok(response);
}

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<ApiResponse<CalenderResponseDto>> getEventById(
            @PathVariable int id
    ){
        CalenderResponseDto event=calenderService.getEventById(id);
        ApiResponse<CalenderResponseDto> response=
                new ApiResponse<>(
                        true,
                        "event fetched by id",
                        event
                );
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<ApiResponse<CalenderResponseDto>> updateEvent(@Valid @PathVariable
            int id, @RequestBody CalenderRequestDto dto){
        CalenderResponseDto updateEvent=calenderService.updateEvent(id, dto);
        ApiResponse<CalenderResponseDto> response=
                new ApiResponse<>(
                        true,
                        "event updated",
                        updateEvent
                );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
   public void deleteEvent(
            @PathVariable int id){
        calenderService.deleteEvent(id);
        ApiResponse<Object> response=
                new ApiResponse<>(
                        true,
                        "event deleted",
                        null
                );
        ResponseEntity.ok(response);
   }
}
