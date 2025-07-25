package hello.servletdemo.web.frontcontroller.v3.controller;

import hello.servletdemo.domain.member.Member;
import hello.servletdemo.domain.member.MemberRepository;
import hello.servletdemo.web.frontcontroller.ModelView;
import hello.servletdemo.web.frontcontroller.MyView;
import hello.servletdemo.web.frontcontroller.v2.ControllerV2;
import hello.servletdemo.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members",members);
        return mv;

    }
}
