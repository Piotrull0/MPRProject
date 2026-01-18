package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.exception.TrainerNotFoundException;
import com.example.project.model.Trainer;
import com.example.project.repository.TrainerRepository;
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
class TrainerServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerService trainerService;

    @Test
    void saveTrainer_Success() throws GymException {
        Trainer trainer = new Trainer();
        trainer.setName("John Doe");

        when(trainerRepository.save(any(Trainer.class))).thenReturn(trainer);

        Trainer saved = trainerService.save(trainer);
        assertEquals("John Doe", saved.getName());
    }

    @Test
    void findById_Success() throws GymException {
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(trainer));

        Trainer found = trainerService.findById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    void findAll_Success() {
        trainerService.findAll();
        verify(trainerRepository, times(1)).findAll();
    }

    @Test
    void delete_Success() throws TrainerNotFoundException {
        when(trainerRepository.existsById(1L)).thenReturn(true);
        trainerService.delete(1L);
        verify(trainerRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_NotFound_ThrowsException() {
        when(trainerRepository.existsById(1L)).thenReturn(false);
        assertThrows(TrainerNotFoundException.class, () -> trainerService.delete(1L));
    }

    @Test
    void update_Success() throws GymException {
        Trainer existing = new Trainer();
        existing.setId(1L);
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(trainerRepository.save(any(Trainer.class))).thenReturn(existing);

        Trainer details = new Trainer("Jane Doe", "Yoga", "Bio");
        Trainer updated = trainerService.update(1L, details);

        assertEquals("Jane Doe", updated.getName());
    }
}
