package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.model.Trainer;
import com.example.project.model.WorkoutPlan;
import com.example.project.repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final TrainerService trainerService;

    public WorkoutPlanService(WorkoutPlanRepository workoutPlanRepository, TrainerService trainerService) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.trainerService = trainerService;
    }

    public List<WorkoutPlan> findAll() {
        return workoutPlanRepository.findAll();
    }

    public WorkoutPlan findById(Long id) throws GymException {
        return workoutPlanRepository.findById(id)
                .orElseThrow(() -> new GymException("WorkoutPlan with id " + id + " not found"));
    }

    @Transactional
    public WorkoutPlan save(WorkoutPlan workoutPlan, Long trainerId) throws GymException {
        if (trainerId != null) {
            Trainer trainer = trainerService.findById(trainerId);
            workoutPlan.setTrainer(trainer);
        }
        if (workoutPlan.getTitle() == null || workoutPlan.getTitle().isEmpty()) {
            throw new GymException("Title is required");
        }
        return workoutPlanRepository.save(workoutPlan);
    }

    @Transactional
    public void delete(Long id) throws GymException {
        if (!workoutPlanRepository.existsById(id)) {
            throw new GymException("WorkoutPlan with id " + id + " not found");
        }
        workoutPlanRepository.deleteById(id);
    }
}
