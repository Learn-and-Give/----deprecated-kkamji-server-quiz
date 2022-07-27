package com.kkamgi.quiz.service;

import com.kkamgi.quiz.domain.Member;
import com.kkamgi.quiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean existsByPassword(String code) {
        return memberRepository.existsByPassword(code);
    }

    public Optional<Member> findOne(String code) {
        return memberRepository.findByPassword(code);
    }

}
