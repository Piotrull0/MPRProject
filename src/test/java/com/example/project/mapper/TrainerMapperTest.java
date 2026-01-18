package com.example.project.mapper;

import com.example.project.dto.TrainerDto;
import com.example.project.model.Trainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainerMapperTest {

    private final TrainerMapper trainerMapper = new TrainerMapper();

    @Test
    void toDto_Success() {
        Trainer trainer = new Trainer("John", "Yoga", "Bio");
        trainer.setId(1L);

        TrainerDto dto = trainerMapper.toDto(trainer);

        assertEquals(1L, dto.getId());
        assertEquals("John", dto.getName());
        assertEquals("Yoga", dto.getSpecialty());
    }

    @Test
    void toEntity_Success() {
        TrainerDto dto = new TrainerDto(1L, "Jane", "Pilates", "Bio");

        Trainer trainer = trainerMapper.toEntity(dto);

        assertEquals(1L, trainer.getId());
        assertEquals("Jane", trainer.getName());
        assertEquals("Pilates", trainer.getSpecialty());
    }
}
