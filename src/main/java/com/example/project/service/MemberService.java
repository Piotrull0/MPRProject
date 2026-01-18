package com.example.project.service;

import com.example.project.exception.GymException;
import com.example.project.exception.MemberNotFoundException;
import com.example.project.model.Member;
import com.example.project.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) throws MemberNotFoundException {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found"));
    }

    @Transactional
    public Member save(Member member) throws GymException {
        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            throw new GymException("Member email cannot be empty");
        }
        return memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) throws MemberNotFoundException {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException("Member with id " + id + " not found");
        }
        memberRepository.deleteById(id);
    }

    @Transactional
    public Member update(Long id, Member memberDetails) throws GymException {
        Member member = findById(id);
        member.setFirstName(memberDetails.getFirstName());
        member.setLastName(memberDetails.getLastName());
        member.setEmail(memberDetails.getEmail());
        return memberRepository.save(member);
    }
}
