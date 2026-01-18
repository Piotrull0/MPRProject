package com.example.project.mapper;

import com.example.project.dto.TrainerDto;
import com.example.project.model.Trainer;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    public TrainerDto toDto(Trainer trainer) {
        if (trainer == null) {
            return null;
        }
        return new TrainerDto(
                trainer.getId(),
                trainer.getName(),
                trainer.getSpecialty(),
                trainer.getBio());
    }

    public Trainer toEntity(TrainerDto dto) {
        if (dto == null) {
            return null;
        }
        Trainer trainer = new Trainer();
        trainer.setId(dto.getId());
        trainer.setName(dto.getName());
        trainer.setSpecialty(dto.getSpecialty());
        trainer.setBio(dto.getBio());
        return trainer;
    }
}
