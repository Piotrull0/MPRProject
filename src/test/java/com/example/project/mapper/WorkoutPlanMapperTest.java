package com.example.project.mapper;

import com.example.project.dto.WorkoutPlanDto;
import com.example.project.model.Trainer;
import com.example.project.model.WorkoutPlan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutPlanMapperTest {

    private final WorkoutPlanMapper workoutPlanMapper = new WorkoutPlanMapper();

    @Test
    void toDto_Success() {
        WorkoutPlan plan = new WorkoutPlan("Title", "Diff", "Goal");
        plan.setId(1L);
        Trainer trainer = new Trainer();
        trainer.setId(2L);
        plan.setTrainer(trainer);

        WorkoutPlanDto dto = workoutPlanMapper.toDto(plan);

        assertEquals(1L, dto.getId());
        assertEquals("Title", dto.getTitle());
        assertEquals(2L, dto.getTrainerId());
    }

    @Test
    void toEntity_Success() {
        WorkoutPlanDto dto = new WorkoutPlanDto(1L, "Title", "Diff", "Goal", 2L);

        WorkoutPlan plan = workoutPlanMapper.toEntity(dto);

        assertEquals(1L, plan.getId());
        assertEquals("Title", plan.getTitle());
    }
}
