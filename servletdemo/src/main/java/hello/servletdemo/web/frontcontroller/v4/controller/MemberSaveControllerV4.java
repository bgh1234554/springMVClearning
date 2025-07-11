package hello.servletdemo.web.frontcontroller.v4.controller;

import hello.servletdemo.domain.member.Member;
import hello.servletdemo.domain.member.MemberRepository;
import hello.servletdemo.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age =  Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

//        ModelView mv =  new ModelView("save-result");
//        //모델은 단순한 map이므로 모델에 뷰에서 필요한 member 객체를 담고 반환한다.
//        mv.getModel().put("member",member);

        //모델을 직접 생성할 필요가 없다. 그냥 put만 하면 된다.
        model.put("member",member);
        return "save-result";
    }
}
