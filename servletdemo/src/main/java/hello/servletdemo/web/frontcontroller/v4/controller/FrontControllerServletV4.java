package hello.servletdemo.web.frontcontroller.v4.controller;

import hello.servletdemo.web.frontcontroller.MyView;
import hello.servletdemo.web.frontcontroller.v4.ControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//하위 모든 요청은 이 서블릿에서 받아들인다.
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    //URL과 호출될 컨트롤러
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new
                MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new
                MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new
                MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller != null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>(); //추가

        //컨트롤러가 직접 뷰의 논리 이름을 반환하기 때문에, 실제 뷰 이름을 이를 통해 찾을 수 있다.
        String viewName = controller.process(paramMap,model);
        //이름으로 실제 위치 생성
        MyView view = viewResolver(viewName);
        view.render(model,request,response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
// 컨트롤러를 구현하는 개발자 입장에서는 이제 군더더기 없는 코드를 작성할 수 있다.
// 프레임워크나 공통 기능이 수고로워야 사용하는 개발자가 편리해진다.
