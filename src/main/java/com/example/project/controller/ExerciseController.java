package com.example.project.controller;

import com.example.project.dto.ExerciseDto;
import com.example.project.exception.GymException;
import com.example.project.mapper.ExerciseMapper;
import com.example.project.model.Exercise;
import com.example.project.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exercises")
@Tag(name = "Exercises", description = "Exercise management API")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;

    public ExerciseController(ExerciseService exerciseService, ExerciseMapper exerciseMapper) {
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
    }

    @GetMapping
    @Operation(summary = "Get all exercises")
    public List<ExerciseDto> getAllExercises() {
        return exerciseService.findAll().stream()
                .map(exerciseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get exercise by ID")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable Long id) throws GymException {
        Exercise exercise = exerciseService.findById(id);
        return ResponseEntity.ok(exerciseMapper.toDto(exercise));
    }

    @PostMapping
    @Operation(summary = "Create a new exercise")
    public ResponseEntity<ExerciseDto> createExercise(@Valid @RequestBody ExerciseDto exerciseDto) throws GymException {
        Exercise exercise = exerciseMapper.toEntity(exerciseDto);
        Exercise savedExercise = exerciseService.save(exercise);
        return ResponseEntity.ok(exerciseMapper.toDto(savedExercise));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an exercise")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) throws GymException {
        exerciseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
