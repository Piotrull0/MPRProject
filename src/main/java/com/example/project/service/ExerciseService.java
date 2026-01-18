package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.model.Exercise;
import com.example.project.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public Exercise findById(Long id) throws GymException {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new GymException("Exercise with id " + id + " not found"));
    }

    @Transactional
    public Exercise save(Exercise exercise) throws GymException {
        if (exercise.getName() == null || exercise.getName().isEmpty()) {
            throw new GymException("Exercise name is required");
        }
        if (exercise.getRepetitions() < 1) {
            throw new GymException("Repetitions must be at least 1");
        }
        return exerciseRepository.save(exercise);
    }

    @Transactional
    public void delete(Long id) throws GymException {
        if (!exerciseRepository.existsById(id)) {
            throw new GymException("Exercise with id " + id + " not found");
        }
        exerciseRepository.deleteById(id);
    }
}
