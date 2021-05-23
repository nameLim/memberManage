package com.inflearn.memberManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")    // 슬래시란 DOM. 첫번째. localhost:8080을 타면 이것이 호출된다.
    public String home(){
        return "home";
    }
}
