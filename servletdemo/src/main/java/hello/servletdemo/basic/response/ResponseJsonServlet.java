package hello.servletdemo.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servletdemo.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * http://localhost:8080/response-json
 *
 */
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        //Content-Type: application/json
        response.setContentType("application/json");
        /*
        application/json 은 스펙상 utf-8 형식을 사용하도록 정의되어 있다.
        그래서 스펙에서 charset=utf-8 과 같은 추가 파라미터를 지원하지 않는다
         */
        response.setCharacterEncoding("UTF-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(30);

        //{"username" : "kim", "age" : 30}
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
/*

HTTP 응답으로 JSON을 반환할 때는 content-type을 application/json 로 지정해야 한다.
Jackson 라이브러리가 제공하는 objectMapper.writeValueAsString() 를 사용하면
객체를 JSON 문자로 변경할 수 있다.

페이지 소스 보기에서 JSON임을 확인할 수 있다.
 */
