package com.example.project.mapper;

import com.example.project.dto.ExerciseDto;
import com.example.project.model.Exercise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseMapperTest {

    private final ExerciseMapper exerciseMapper = new ExerciseMapper();

    @Test
    void toDto_Success() {
        Exercise exercise = new Exercise("Pushup", 10, "Str", "Chest");
        exercise.setId(1L);

        ExerciseDto dto = exerciseMapper.toDto(exercise);

        assertEquals(1L, dto.getId());
        assertEquals("Pushup", dto.getName());
    }

    @Test
    void toEntity_Success() {
        ExerciseDto dto = new ExerciseDto(1L, "Squat", 5, "Legs", "Quads");

        Exercise exercise = exerciseMapper.toEntity(dto);

        assertEquals(1L, exercise.getId());
        assertEquals("Squat", exercise.getName());
    }
}
