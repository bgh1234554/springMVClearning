package hello.servletdemo.web.frontcontroller.v4.controller;

import hello.servletdemo.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model){
        return "new-form";
    }
}
/*
ModelView 를 생성할 때 new-form 이라는 view의 논리적인 이름을 지정한다.
실제 물리적인 이름은 프론트 컨트롤러에서 처리한다. 예외처리도 싹 다 사라졌다.
 */