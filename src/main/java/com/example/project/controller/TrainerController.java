package com.example.project.controller;

import com.example.project.dto.TrainerDto;
import com.example.project.exception.GymException;
import com.example.project.mapper.TrainerMapper;
import com.example.project.model.Trainer;
import com.example.project.service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trainers")
@Tag(name = "Trainers", description = "Trainer management API")
public class TrainerController {

    private final TrainerService trainerService;
    private final TrainerMapper trainerMapper;

    public TrainerController(TrainerService trainerService, TrainerMapper trainerMapper) {
        this.trainerService = trainerService;
        this.trainerMapper = trainerMapper;
    }

    @GetMapping
    @Operation(summary = "Get all trainers")
    public List<TrainerDto> getAllTrainers() {
        return trainerService.findAll().stream()
                .map(trainerMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get trainer by ID")
    public ResponseEntity<TrainerDto> getTrainerById(@PathVariable Long id) throws GymException {
        Trainer trainer = trainerService.findById(id);
        return ResponseEntity.ok(trainerMapper.toDto(trainer));
    }

    @PostMapping
    @Operation(summary = "Create a new trainer")
    public ResponseEntity<TrainerDto> createTrainer(@Valid @RequestBody TrainerDto trainerDto) throws GymException {
        Trainer trainer = trainerMapper.toEntity(trainerDto);
        Trainer savedTrainer = trainerService.save(trainer);
        return ResponseEntity.ok(trainerMapper.toDto(savedTrainer));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing trainer")
    public ResponseEntity<TrainerDto> updateTrainer(@PathVariable Long id, @Valid @RequestBody TrainerDto trainerDto)
            throws GymException {
        Trainer trainerDetails = trainerMapper.toEntity(trainerDto);
        Trainer updatedTrainer = trainerService.update(id, trainerDetails);
        return ResponseEntity.ok(trainerMapper.toDto(updatedTrainer));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a trainer")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) throws GymException {
        trainerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
