package com.kkamgi.quiz.repository;

import com.kkamgi.quiz.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByPassword(String password);

    Optional<Member> findByPassword(String password);
}
