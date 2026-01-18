package com.example.project.dto;

public class TrainerDto {
    private Long id;
    private String name;
    private String specialty;
    private String bio;

    public TrainerDto() {
    }

    public TrainerDto(Long id, String name, String specialty, String bio) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
