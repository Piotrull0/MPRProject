package com.example.project.mapper;

import com.example.project.dto.MemberRequestDto;
import com.example.project.dto.MemberResponseDto;
import com.example.project.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    private final MembershipMapper membershipMapper;

    public MemberMapper(MembershipMapper membershipMapper) {
        this.membershipMapper = membershipMapper;
    }

    public MemberResponseDto toDto(Member member) {
        if (member == null) {
            return null;
        }
        return new MemberResponseDto(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getEmail(),
                membershipMapper.toDto(member.getMembership()));
    }

    public Member toEntity(MemberRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Member member = new Member();
        member.setFirstName(dto.getFirstName());
        member.setLastName(dto.getLastName());
        member.setEmail(dto.getEmail());
        return member;
    }
}
