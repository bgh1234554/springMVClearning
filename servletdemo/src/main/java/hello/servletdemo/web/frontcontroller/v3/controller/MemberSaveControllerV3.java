package hello.servletdemo.web.frontcontroller.v3.controller;

import hello.servletdemo.domain.member.Member;
import hello.servletdemo.domain.member.MemberRepository;
import hello.servletdemo.web.frontcontroller.ModelView;
import hello.servletdemo.web.frontcontroller.MyView;
import hello.servletdemo.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        //파라미터가 필요한거지, request, response가 필요한 것이기 때문에 request, response가 파라미터에서 사라졌다.
        //별도의 모델 객체가 request 대신 데이터를 전달하게 된다.
        String username = paramMap.get("username");
        int age =  Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv =  new ModelView("save-result");
        //모델은 단순한 map이므로 모델에 뷰에서 필요한 member 객체를 담고 반환한다.
        mv.getModel().put("member",member);
        return mv;
    }
}
