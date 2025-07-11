package hello.servletdemo.web.frontcontroller.v2;

import hello.servletdemo.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
이제 MVC 프레임워크를 버전별로 하나씩 만들어보면서 스프링 MVC의 탄생과정을 알아본다.

 */
public interface ControllerV2 {
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
