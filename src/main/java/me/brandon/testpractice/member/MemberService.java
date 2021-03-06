package me.brandon.testpractice.member;

import me.brandon.testpractice.domain.Member;
import me.brandon.testpractice.domain.Study;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);
}