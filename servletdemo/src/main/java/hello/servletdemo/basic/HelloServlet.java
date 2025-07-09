package hello.servletdemo.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="helloServlet",urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override //Ctrl + O 후 protected
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //soutm - 메서드 명 프린트
        System.out.println("HelloServlet.service");
        //soutv 변수 값 프린트
        System.out.println("request = "+request);
        System.out.println("response = "+response);

        String username = request.getParameter("username");
        System.out.println("username = "+username);

        //HTTP Response에 값 찍기
        response.setContentType("text/plain"); //단순 문자
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("hello"+username);
        
        //개발자 도구의 네트워크 탭에서 Response Headers와 같은 것들 확인 가능
    }
}
/*
HttpServletRequest
- 서블릿은 개발자가 HTTP 요청 메시지를 편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱한다.
그리고 그 결과를 HttpServletRequest 객체에 담아서 제공한다.
 */