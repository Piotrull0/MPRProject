package com.example.project.dto;

import java.time.LocalDateTime;

public class GymClassDto {
    private Long id;
    private String name;
    private LocalDateTime scheduleTime;
    private int capacity;

    public GymClassDto() {
    }

    public GymClassDto(Long id, String name, LocalDateTime scheduleTime, int capacity) {
        this.id = id;
        this.name = name;
        this.scheduleTime = scheduleTime;
        this.capacity = capacity;
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

    public LocalDateTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalDateTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
