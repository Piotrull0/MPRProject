package com.example.project.dto;

import java.time.LocalDate;

public class MembershipDto {
    private Long id;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;

    public MembershipDto() {
    }

    public MembershipDto(Long id, String type, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
