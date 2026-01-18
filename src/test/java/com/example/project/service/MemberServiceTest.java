package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.exception.MemberNotFoundException;
import com.example.project.model.Member;
import com.example.project.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void saveMember_Success() throws GymException {
        Member member = new Member();
        member.setEmail("test@test.com");

        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member saved = memberService.save(member);
        assertNotNull(saved);
        assertEquals("test@test.com", saved.getEmail());
    }

    @Test
    void saveMember_NoEmail_ThrowsException() {
        Member member = new Member();

        assertThrows(GymException.class, () -> memberService.save(member));
    }

    @Test
    void findById_Success() throws GymException {
        Member member = new Member();
        member.setId(1L);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Member found = memberService.findById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    void findAll_Success() {
        memberService.findAll();
        verify(memberRepository, times(1)).findAll();
    }

    @Test
    void delete_Success() throws MemberNotFoundException {
        when(memberRepository.existsById(1L)).thenReturn(true);
        memberService.delete(1L);
        verify(memberRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_NotFound_ThrowsException() {
        when(memberRepository.existsById(1L)).thenReturn(false);
        assertThrows(MemberNotFoundException.class, () -> memberService.delete(1L));
    }

    @Test
    void update_Success() throws GymException {
        Member existing = new Member();
        existing.setId(1L);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(memberRepository.save(any(Member.class))).thenReturn(existing);

        Member details = new Member("Jane", "Doe", "jane@test.com");
        Member updated = memberService.update(1L, details);

        assertEquals("Jane", updated.getFirstName());
        assertEquals("Doe", updated.getLastName());
        assertEquals("jane@test.com", updated.getEmail());
    }
}
