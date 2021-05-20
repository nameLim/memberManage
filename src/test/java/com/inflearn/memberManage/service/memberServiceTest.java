package com.inflearn.memberManage.service;

import com.inflearn.memberManage.domain.Member;
import com.inflearn.memberManage.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.*;

class memberServiceTest {

//    MemberService 클래스에서 memberRepository 를 Constructor 생성으로 변경함으로써
//    BeforeEach로 넣었음.
//    이런것을 dependency injection 이라고 한다.(DI)
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService( memberRepository );
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
        // 사실상 test에서 만든 memberRepository랑 memberService에 있는 final Repository랑 다른 인스턴스임
        // 물론 지금은 store가 static으로 되어있어 문제는 없겠지만, static이 아닐 경우 다른 DB가 되면서 바로 문제가 생긴다.
        // 즉, 여기서 지금은 다른 memberRepository가 테스트 되고 있는 것이다.
        // 같은 memberRepository 사용하게 하는 방법 !
        // ==>  MemberService 클래스에서 final memberRepository의 new 선언을 지우고
        //      Constructor를 만든다
        //  즉, 안에서 만드는 것(new 선언)이 아니라, 외부에서 넣어주록 만드는 것(constructor)이다.
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName( "hello" );

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join( member1 );
        
        // 이것 때문에 try catch 하는 것이 애매함. 다른 것 제공
//        try{
//            memberService.join( member2 ); // 예외 발생해야 함
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.113");
//        }

        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));
        /*
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        */
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}