package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.exception.TrainerNotFoundException;
import com.example.project.model.Trainer;
import com.example.project.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    protected TrainerService() {
        this.trainerRepository = null;
    }

    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    public Trainer findById(Long id) throws TrainerNotFoundException {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer with id " + id + " not found"));
    }

    @Transactional
    public Trainer save(Trainer trainer) throws GymException {
        if (trainer.getName() == null || trainer.getName().isEmpty()) {
            throw new GymException("Trainer name cannot be empty");
        }
        return trainerRepository.save(trainer);
    }

    @Transactional
    public void delete(Long id) throws TrainerNotFoundException {
        if (!trainerRepository.existsById(id)) {
            throw new TrainerNotFoundException("Trainer with id " + id + " not found");
        }
        trainerRepository.deleteById(id);
    }

    @Transactional
    public Trainer update(Long id, Trainer trainerDetails) throws GymException {
        Trainer trainer = findById(id);
        trainer.setName(trainerDetails.getName());
        trainer.setSpecialty(trainerDetails.getSpecialty());
        trainer.setBio(trainerDetails.getBio());
        return trainerRepository.save(trainer);
    }
}
