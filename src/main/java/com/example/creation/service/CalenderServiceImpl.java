package com.example.creation.service;

import com.example.creation.dto.request.CalenderRequestDto;
import com.example.creation.dto.response.CalenderResponseDto;
import com.example.creation.entity.CalenderEvent;
import com.example.creation.exception.ResourceAlreadyExistsException;
import com.example.creation.mapper.CalenderMapper;
import com.example.creation.repository.CalenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderServiceImpl implements CalenderService{

    private final CalenderRepository calenderRepository;
    private final CalenderMapper calenderMapper;
    @Override
    public CalenderResponseDto createEvent(CalenderRequestDto dto) {
        CalenderEvent event=calenderMapper.toEntity(dto);
        CalenderEvent saveEvent=calenderRepository.save(event);
        return calenderMapper.toResponse(saveEvent);
    }

    @Override
    public List<CalenderResponseDto> getAllEvents() {
        return calenderRepository.findAll().stream()
                .map(calenderMapper::toResponse).toList();
    }

    @Override
    public CalenderResponseDto getEventById(int id) {
        CalenderEvent event=calenderRepository.findById(id).orElseThrow(()->
                new ResourceAlreadyExistsException("event not found"));

        return calenderMapper.toResponse(event) ;
    }

    @Override
    public CalenderResponseDto updateEvent(int id, CalenderRequestDto dto) {
        CalenderEvent event=calenderRepository.findById(id).orElseThrow(()->
                new ResourceAlreadyExistsException("event not found"));
        event.setTitle(dto.title());
        event.setDescription(dto.description());
        event.setEventDate(dto.eventDate());
        CalenderEvent upadateEvent=calenderRepository.save(event);

        return calenderMapper.toResponse(upadateEvent);
    }

    @Override
    public void deleteEvent(int id) {
        CalenderEvent event=calenderRepository.findById(id).orElseThrow(()->
                new ResourceAlreadyExistsException(
                        "event not found"
                ));
        calenderRepository.deleteById(id);

    }
}
