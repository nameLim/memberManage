package com.inflearn.memberManage.repository;
import com.inflearn.memberManage.domain.Member;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put( member.getId(), member );
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable( store.get( id ) ); //Optional로 감싸면 null일 경우를 클라이언트에서 무언가를 할 수 있다. ->나중에 설명해줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
