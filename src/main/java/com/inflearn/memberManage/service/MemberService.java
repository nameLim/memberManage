package com.inflearn.memberManage.service;

import com.inflearn.memberManage.domain.Member;
import com.inflearn.memberManage.repository.MemberRepository;
import com.inflearn.memberManage.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join( Member member ) {

        validateDupMember(member);  // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupMember(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent( m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        memberRepository.findByName(member.getName())
                .ifPresent( m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne( Long memberId ){
        return memberRepository.findById(memberId);
    }
}
