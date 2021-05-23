package com.inflearn.memberManage.controller;

import com.inflearn.memberManage.domain.Member;
import com.inflearn.memberManage.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("members/new")
    public String createForm(){
        return "members/createMembersForm";
    }

    @PostMapping("/members/new") //MemberForm 클래스로 조인 시키는 것
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //home화면으로 보내기
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
