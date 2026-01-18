package com.example.project.controller;

import com.example.project.dto.WorkoutPlanDto;
import com.example.project.exception.GymException;
import com.example.project.mapper.WorkoutPlanMapper;
import com.example.project.model.WorkoutPlan;
import com.example.project.service.WorkoutPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workout-plans")
@Tag(name = "Workout Plans", description = "Workout Plan management API")
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;
    private final WorkoutPlanMapper workoutPlanMapper;

    public WorkoutPlanController(WorkoutPlanService workoutPlanService, WorkoutPlanMapper workoutPlanMapper) {
        this.workoutPlanService = workoutPlanService;
        this.workoutPlanMapper = workoutPlanMapper;
    }

    @GetMapping
    @Operation(summary = "Get all workout plans")
    public List<WorkoutPlanDto> getAllWorkoutPlans() {
        return workoutPlanService.findAll().stream()
                .map(workoutPlanMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get workout plan by ID")
    public ResponseEntity<WorkoutPlanDto> getWorkoutPlanById(@PathVariable Long id) throws GymException {
        WorkoutPlan workoutPlan = workoutPlanService.findById(id);
        return ResponseEntity.ok(workoutPlanMapper.toDto(workoutPlan));
    }

    @PostMapping
    @Operation(summary = "Create a new workout plan")
    public ResponseEntity<WorkoutPlanDto> createWorkoutPlan(@Valid @RequestBody WorkoutPlanDto workoutPlanDto)
            throws GymException {
        WorkoutPlan workoutPlan = workoutPlanMapper.toEntity(workoutPlanDto);
        WorkoutPlan savedPlan = workoutPlanService.save(workoutPlan, workoutPlanDto.getTrainerId());
        return ResponseEntity.ok(workoutPlanMapper.toDto(savedPlan));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a workout plan")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) throws GymException {
        workoutPlanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
