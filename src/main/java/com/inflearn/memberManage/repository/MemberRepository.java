package com.inflearn.memberManage.repository;
import com.inflearn.memberManage.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    
    //Optional 이란? java8에 들어간 기능임
    //id나 name으로 찾았을 때 없는 경우 return 값이 null일 것임
    //이 때 null을 그대로 반환하는 것이 아니라, Optional이라는 것으로 한번 감싸서 return 하는 것을 선호함
    Optional<Member> findById(Long id); // id로 회원을 찾는 역할
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
