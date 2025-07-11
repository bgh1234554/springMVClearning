package hello.servletdemo.web.frontcontroller.v2.controller;

import hello.servletdemo.web.frontcontroller.MyView;
import hello.servletdemo.web.frontcontroller.v2.ControllerV2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//하위 모든 요청은 이 서블릿에서 받아들인다.
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    //URL과 호출될 컨트롤러
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new
                MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new
                MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new
                MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("FrontControllerServletV2.service");
        /*
        먼저 requestURI 를 조회해서 실제 호출할 컨트롤러를 controllerMap 에서 찾는다.
        만약 없다면 404(SC_NOT_FOUND) 상태 코드를 반환한다.
         */
        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller != null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.process(request, response);
        //view.render() 를 호출하면 forward 로직을 수행해서 JSP가 실행된다.
        view.render(request, response);
    }
}
