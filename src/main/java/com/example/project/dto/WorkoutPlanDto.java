package com.example.project.dto;

public class WorkoutPlanDto {
    private Long id;
    private String title;
    private String difficulty;
    private String goal;
    private Long trainerId;

    public WorkoutPlanDto() {
    }

    public WorkoutPlanDto(Long id, String title, String difficulty, String goal, Long trainerId) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.goal = goal;
        this.trainerId = trainerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }
}
