package com.example.project.mapper;

import com.example.project.dto.MembershipDto;
import com.example.project.model.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {

    public MembershipDto toDto(Membership membership) {
        if (membership == null) {
            return null;
        }
        return new MembershipDto(
                membership.getId(),
                membership.getType(),
                membership.getStartDate(),
                membership.getEndDate());
    }

    public Membership toEntity(MembershipDto dto) {
        if (dto == null) {
            return null;
        }
        Membership membership = new Membership();
        membership.setId(dto.getId());
        membership.setType(dto.getType());
        membership.setStartDate(dto.getStartDate());
        membership.setEndDate(dto.getEndDate());
        return membership;
    }
}
