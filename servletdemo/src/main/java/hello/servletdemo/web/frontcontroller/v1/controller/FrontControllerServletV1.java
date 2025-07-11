package hello.servletdemo.web.frontcontroller.v1.controller;

import hello.servletdemo.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//하위 모든 요청은 이 서블릿에서 받아들인다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    //URL과 호출될 컨트롤러
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        //요청 URI 별 컨트롤러 호출 목록
        controllerMap.put("/front-controller/v1/members/new-form", new
                MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new
                MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new
                MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        /*
        먼저 requestURI 를 조회해서 실제 호출할 컨트롤러를 controllerMap 에서 찾는다.
        만약 없다면 404(SC_NOT_FOUND) 상태 코드를 반환한다.
         */
        //해당 요청이 어느 컨트롤러로 가야하는지 찾는다.
        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller != null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        controller.process(request, response);
    }
}
