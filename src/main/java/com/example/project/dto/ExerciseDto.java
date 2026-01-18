package com.example.project.dto;

public class ExerciseDto {
    private Long id;
    private String name;
    private int repetitions;
    private String type;
    private String muscleGroup;

    public ExerciseDto() {
    }

    public ExerciseDto(Long id, String name, int repetitions, String type, String muscleGroup) {
        this.id = id;
        this.name = name;
        this.repetitions = repetitions;
        this.type = type;
        this.muscleGroup = muscleGroup;
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

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
