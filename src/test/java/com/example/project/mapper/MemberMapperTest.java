package com.example.project.mapper;

import com.example.project.dto.MemberRequestDto;
import com.example.project.dto.MemberResponseDto;
import com.example.project.dto.MembershipDto;
import com.example.project.model.Member;
import com.example.project.model.Membership;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberMapperTest {

    private final MembershipMapper membershipMapper = new MembershipMapper();
    private final MemberMapper memberMapper = new MemberMapper(membershipMapper);

    @Test
    void toDto_Success() {
        Member member = new Member("John", "Doe", "john@test.com");
        member.setId(1L);
        Membership membership = new Membership();
        membership.setId(2L);
        member.setMembership(membership);

        MemberResponseDto dto = memberMapper.toDto(member);

        assertEquals(1L, dto.getId());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("john@test.com", dto.getEmail());
        assertNotNull(dto.getMembership());
    }

    @Test
    void toEntity_Success() {
        MemberRequestDto dto = new MemberRequestDto("Jane", "Doe", "jane@test.com");

        Member member = memberMapper.toEntity(dto);

        assertEquals("Jane", member.getFirstName());
        assertEquals("Doe", member.getLastName());
        assertEquals("jane@test.com", member.getEmail());
    }
}
