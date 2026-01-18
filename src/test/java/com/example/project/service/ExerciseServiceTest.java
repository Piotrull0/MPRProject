package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.model.Exercise;
import com.example.project.repository.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseService exerciseService;

    @Test
    void saveExercise_Success() throws GymException {
        Exercise exercise = new Exercise("Pushup", 10, "Strength", "Chest");

        when(exerciseRepository.save(any(Exercise.class))).thenReturn(exercise);

        Exercise saved = exerciseService.save(exercise);
        assertEquals("Pushup", saved.getName());
    }

    @Test
    void saveExercise_InvalidRepetitions_ThrowsException() {
        Exercise exercise = new Exercise("Pushup", 0, "Strength", "Chest");

        assertThrows(GymException.class, () -> exerciseService.save(exercise));
    }

    @Test
    void findAll_Success() {
        exerciseService.findAll();
        verify(exerciseRepository, times(1)).findAll();
    }

    @Test
    void findById_Success() throws GymException {
        Exercise exercise = new Exercise();
        exercise.setId(1L);
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));

        Exercise found = exerciseService.findById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    void delete_Success() throws GymException {
        when(exerciseRepository.existsById(1L)).thenReturn(true);
        exerciseService.delete(1L);
        verify(exerciseRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_NotFound_ThrowsException() {
        when(exerciseRepository.existsById(1L)).thenReturn(false);
        assertThrows(GymException.class, () -> exerciseService.delete(1L));
    }
}
