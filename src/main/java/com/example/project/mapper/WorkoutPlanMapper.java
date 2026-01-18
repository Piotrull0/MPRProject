package com.example.project.mapper;

import com.example.project.dto.WorkoutPlanDto;
import com.example.project.model.WorkoutPlan;
import org.springframework.stereotype.Component;

@Component
public class WorkoutPlanMapper {

    public WorkoutPlanDto toDto(WorkoutPlan workoutPlan) {
        if (workoutPlan == null) {
            return null;
        }
        Long trainerId = workoutPlan.getTrainer() != null ? workoutPlan.getTrainer().getId() : null;
        return new WorkoutPlanDto(
                workoutPlan.getId(),
                workoutPlan.getTitle(),
                workoutPlan.getDifficulty(),
                workoutPlan.getGoal(),
                trainerId);
    }

    public WorkoutPlan toEntity(WorkoutPlanDto dto) {
        if (dto == null) {
            return null;
        }
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setId(dto.getId());
        workoutPlan.setTitle(dto.getTitle());
        workoutPlan.setDifficulty(dto.getDifficulty());
        workoutPlan.setGoal(dto.getGoal());
        return workoutPlan;
    }
}
