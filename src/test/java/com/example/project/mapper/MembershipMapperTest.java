package com.example.project.mapper;

import com.example.project.dto.MembershipDto;
import com.example.project.model.Membership;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MembershipMapperTest {

    private final MembershipMapper membershipMapper = new MembershipMapper();

    @Test
    void toDto_Success() {
        Membership membership = new Membership("Gold", LocalDate.now(), LocalDate.now().plusMonths(1));
        membership.setId(1L);

        MembershipDto dto = membershipMapper.toDto(membership);

        assertEquals(1L, dto.getId());
        assertEquals("Gold", dto.getType());
    }

    @Test
    void toEntity_Success() {
        MembershipDto dto = new MembershipDto(1L, "Silver", LocalDate.now(), LocalDate.now().plusMonths(1));

        Membership membership = membershipMapper.toEntity(dto);

        assertEquals(1L, membership.getId());
        assertEquals("Silver", membership.getType());
    }
}
