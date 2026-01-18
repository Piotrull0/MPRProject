package com.example.project.mapper;

import com.example.project.dto.GymClassDto;
import com.example.project.model.GymClass;
import org.springframework.stereotype.Component;

@Component
public class GymClassMapper {

    public GymClassDto toDto(GymClass gymClass) {
        if (gymClass == null) {
            return null;
        }
        return new GymClassDto(
                gymClass.getId(),
                gymClass.getName(),
                gymClass.getScheduleTime(),
                gymClass.getCapacity());
    }

    public GymClass toEntity(GymClassDto dto) {
        if (dto == null) {
            return null;
        }
        GymClass gymClass = new GymClass();
        gymClass.setId(dto.getId());
        gymClass.setName(dto.getName());
        gymClass.setScheduleTime(dto.getScheduleTime());
        gymClass.setCapacity(dto.getCapacity());
        return gymClass;
    }
}
