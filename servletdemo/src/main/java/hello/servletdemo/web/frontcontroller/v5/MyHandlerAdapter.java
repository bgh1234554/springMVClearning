package hello.servletdemo.web.frontcontroller.v5;

import hello.servletdemo.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    //어댑터가 해당 핸들러(컨트롤러의 확장판)을 처리할 수 있는지 나타낸다
    boolean supports(Object handler);

    //실제 컨트롤러를 호출 해 결과로 ModelView를 반환한다.
    //어댑터를 통해 실제 컨트롤러가 호출된다.
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
/*
다양한 종류의 컨트롤러를 호출할 수 있게 해주는 어댑터 인터페이스
 */
