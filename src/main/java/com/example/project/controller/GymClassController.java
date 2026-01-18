package com.example.project.controller;

import com.example.project.dto.GymClassDto;
import com.example.project.exception.GymException;
import com.example.project.mapper.GymClassMapper;
import com.example.project.model.GymClass;
import com.example.project.service.GymClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gym-classes")
@Tag(name = "Gym Classes", description = "Gym Class management API")
public class GymClassController {

    private final GymClassService gymClassService;
    private final GymClassMapper gymClassMapper;

    public GymClassController(GymClassService gymClassService, GymClassMapper gymClassMapper) {
        this.gymClassService = gymClassService;
        this.gymClassMapper = gymClassMapper;
    }

    @GetMapping
    @Operation(summary = "Get all gym classes")
    public List<GymClassDto> getAllGymClasses() {
        return gymClassService.findAll().stream()
                .map(gymClassMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get gym class by ID")
    public ResponseEntity<GymClassDto> getGymClassById(@PathVariable Long id) throws GymException {
        GymClass gymClass = gymClassService.findById(id);
        return ResponseEntity.ok(gymClassMapper.toDto(gymClass));
    }

    @PostMapping
    @Operation(summary = "Create a new gym class")
    public ResponseEntity<GymClassDto> createGymClass(@Valid @RequestBody GymClassDto gymClassDto) throws GymException {
        GymClass gymClass = gymClassMapper.toEntity(gymClassDto);
        GymClass savedClass = gymClassService.save(gymClass);
        return ResponseEntity.ok(gymClassMapper.toDto(savedClass));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a gym class")
    public ResponseEntity<Void> deleteGymClass(@PathVariable Long id) throws GymException {
        gymClassService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
