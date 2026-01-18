package com.example.project.controller;

import com.example.project.dto.MemberRequestDto;
import com.example.project.dto.MemberResponseDto;
import com.example.project.exception.GymException;
import com.example.project.mapper.MemberMapper;
import com.example.project.model.Member;
import com.example.project.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Members", description = "Member management API")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping
    @Operation(summary = "Get all members")
    public List<MemberResponseDto> getAllMembers() {
        return memberService.findAll().stream()
                .map(memberMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get member by ID")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Long id) throws GymException {
        Member member = memberService.findById(id);
        return ResponseEntity.ok(memberMapper.toDto(member));
    }

    @PostMapping
    @Operation(summary = "Create a new member")
    public ResponseEntity<MemberResponseDto> createMember(@Valid @RequestBody MemberRequestDto memberRequestDto)
            throws GymException {
        Member member = memberMapper.toEntity(memberRequestDto);
        Member savedMember = memberService.save(member);
        return ResponseEntity.ok(memberMapper.toDto(savedMember));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing member")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long id,
            @Valid @RequestBody MemberRequestDto memberRequestDto) throws GymException {
        Member memberDetails = memberMapper.toEntity(memberRequestDto);
        Member updatedMember = memberService.update(id, memberDetails);
        return ResponseEntity.ok(memberMapper.toDto(updatedMember));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a member")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) throws GymException {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
