package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.model.Trainer;
import com.example.project.model.WorkoutPlan;
import com.example.project.repository.TrainerRepository;
import com.example.project.repository.WorkoutPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutPlanServiceTest {

    @Mock
    private WorkoutPlanRepository workoutPlanRepository;

    @Mock
    private TrainerRepository trainerRepository;

    private WorkoutPlanService workoutPlanService;

    @BeforeEach
    void setUp() {
        TrainerService trainerService = new TrainerService(trainerRepository);
        workoutPlanService = new WorkoutPlanService(workoutPlanRepository, trainerService);
    }

    @Test
    void saveWorkoutPlan_Success() throws GymException {
        WorkoutPlan plan = new WorkoutPlan("Beginner", "Easy", "Fat Loss");
        Trainer trainer = new Trainer();
        trainer.setId(1L);

        when(trainerRepository.findById(1L)).thenReturn(Optional.of(trainer));
        when(workoutPlanRepository.save(any(WorkoutPlan.class))).thenReturn(plan);

        WorkoutPlan saved = workoutPlanService.save(plan, 1L);
        assertEquals("Beginner", saved.getTitle());
    }

    @Test
    void findAll_Success() {
        workoutPlanService.findAll();
        verify(workoutPlanRepository, times(1)).findAll();
    }

    @Test
    void findById_Success() throws GymException {
        WorkoutPlan plan = new WorkoutPlan();
        plan.setId(1L);
        when(workoutPlanRepository.findById(1L)).thenReturn(Optional.of(plan));

        WorkoutPlan found = workoutPlanService.findById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    void delete_Success() throws GymException {
        when(workoutPlanRepository.existsById(1L)).thenReturn(true);
        workoutPlanService.delete(1L);
        verify(workoutPlanRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_NotFound_ThrowsException() {
        when(workoutPlanRepository.existsById(1L)).thenReturn(false);
        assertThrows(GymException.class, () -> workoutPlanService.delete(1L));
    }
}
