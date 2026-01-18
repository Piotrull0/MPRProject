package com.example.project.mapper;

import com.example.project.dto.ExerciseDto;
import com.example.project.model.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    public ExerciseDto toDto(Exercise exercise) {
        if (exercise == null) {
            return null;
        }
        return new ExerciseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getRepetitions(),
                exercise.getType(),
                exercise.getMuscleGroup());
    }

    public Exercise toEntity(ExerciseDto dto) {
        if (dto == null) {
            return null;
        }
        Exercise exercise = new Exercise();
        exercise.setId(dto.getId());
        exercise.setName(dto.getName());
        exercise.setRepetitions(dto.getRepetitions());
        exercise.setType(dto.getType());
        exercise.setMuscleGroup(dto.getMuscleGroup());
        return exercise;
    }
}
