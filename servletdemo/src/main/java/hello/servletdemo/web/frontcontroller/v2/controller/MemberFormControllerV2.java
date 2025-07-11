package hello.servletdemo.web.frontcontroller.v2.controller;

import hello.servletdemo.web.frontcontroller.MyView;
import hello.servletdemo.web.frontcontroller.v2.ControllerV2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
        //이제 직접 dispatcher 생성 없이 MyView 객체 생성해 링크만 넣으면 된다.
    }
}
