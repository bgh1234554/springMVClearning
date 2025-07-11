package hello.servletdemo.web.frontcontroller.v3.controller;

import hello.servletdemo.web.frontcontroller.ModelView;
import hello.servletdemo.web.frontcontroller.MyView;
import hello.servletdemo.web.frontcontroller.v2.ControllerV2;
import hello.servletdemo.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        //경로 대신에 이름만 들어가 있는 것을 확인할 수 있다.
        return new ModelView("new-form");
    }
}
/*
ModelView 를 생성할 때 new-form 이라는 view의 논리적인 이름을 지정한다.
실제 물리적인 이름은 프론트 컨트롤러에서 처리한다. 예외처리도 싹 다 사라졌다.
 */