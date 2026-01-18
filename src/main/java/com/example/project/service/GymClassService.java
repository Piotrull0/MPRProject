package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.model.GymClass;
import com.example.project.repository.GymClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GymClassService {

    private final GymClassRepository gymClassRepository;

    public GymClassService(GymClassRepository gymClassRepository) {
        this.gymClassRepository = gymClassRepository;
    }

    public List<GymClass> findAll() {
        return gymClassRepository.findAll();
    }

    public GymClass findById(Long id) throws GymException {
        return gymClassRepository.findById(id)
                .orElseThrow(() -> new GymException("GymClass with id " + id + " not found"));
    }

    @Transactional
    public GymClass save(GymClass gymClass) throws GymException {
        if (gymClass.getName() == null || gymClass.getName().isEmpty()) {
            throw new GymException("GymClass name is required");
        }
        if (gymClass.getCapacity() < 1) {
            throw new GymException("Capacity must be at least 1");
        }
        return gymClassRepository.save(gymClass);
    }

    @Transactional
    public void delete(Long id) throws GymException {
        if (!gymClassRepository.existsById(id)) {
            throw new GymException("GymClass with id " + id + " not found");
        }
        gymClassRepository.deleteById(id);
    }
}
