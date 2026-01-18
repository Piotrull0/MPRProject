package com.example.project.mapper;

import com.example.project.dto.GymClassDto;
import com.example.project.model.GymClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GymClassMapperTest {

    private final GymClassMapper gymClassMapper = new GymClassMapper();

    @Test
    void toDto_Success() {
        GymClass gymClass = new GymClass("Yoga", LocalDateTime.now(), 20);
        gymClass.setId(1L);

        GymClassDto dto = gymClassMapper.toDto(gymClass);

        assertEquals(1L, dto.getId());
        assertEquals("Yoga", dto.getName());
    }

    @Test
    void toEntity_Success() {
        GymClassDto dto = new GymClassDto(1L, "Pilates", LocalDateTime.now(), 15);

        GymClass gymClass = gymClassMapper.toEntity(dto);

        assertEquals(1L, gymClass.getId());
        assertEquals("Pilates", gymClass.getName());
    }
}
