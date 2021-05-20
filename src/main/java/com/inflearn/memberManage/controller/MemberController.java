package com.inflearn.memberManage.controller;

import com.inflearn.memberManage.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;
    // 서비스는 하나만 생성해서 공용으로 사용하면 된다. 때문에 constructor 생성

    // Autowired : 스프링 컨테이너에서 MemberService를 찾아서 memberService 에 연결시켜준다.
    // 필요한 것 : MemberService 클래스에 @Service 어노테이션을 넣어줘야한다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
