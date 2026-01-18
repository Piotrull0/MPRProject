package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.model.GymClass;
import com.example.project.repository.GymClassRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GymClassServiceTest {

    @Mock
    private GymClassRepository gymClassRepository;

    @InjectMocks
    private GymClassService gymClassService;

    @Test
    void saveGymClass_Success() throws GymException {
        GymClass gymClass = new GymClass("Yoga", LocalDateTime.now(), 20);

        when(gymClassRepository.save(any(GymClass.class))).thenReturn(gymClass);

        GymClass saved = gymClassService.save(gymClass);
        assertEquals("Yoga", saved.getName());
    }

    @Test
    void findAll_Success() {
        gymClassService.findAll();
        verify(gymClassRepository, times(1)).findAll();
    }

    @Test
    void findById_Success() throws GymException {
        GymClass gymClass = new GymClass();
        gymClass.setId(1L);
        when(gymClassRepository.findById(1L)).thenReturn(Optional.of(gymClass));

        GymClass found = gymClassService.findById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    void delete_Success() throws GymException {
        when(gymClassRepository.existsById(1L)).thenReturn(true);
        gymClassService.delete(1L);
        verify(gymClassRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_NotFound_ThrowsException() {
        when(gymClassRepository.existsById(1L)).thenReturn(false);
        assertThrows(GymException.class, () -> gymClassService.delete(1L));
    }
}
