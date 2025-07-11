package hello.servletdemo.web.frontcontroller.v3.controller;

import hello.servletdemo.web.frontcontroller.ModelView;
import hello.servletdemo.web.frontcontroller.MyView;
import hello.servletdemo.web.frontcontroller.v2.ControllerV2;
import hello.servletdemo.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servletdemo.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//하위 모든 요청은 이 서블릿에서 받아들인다.
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    //URL과 호출될 컨트롤러
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new
                MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new
                MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new
                MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller != null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //request에서 파라미터 값을 다 꺼내서 Map에 넣어줘야 한다.
        Map<String, String> paramMap = createParamMap(request);
        //이후 그 파라미터가 담긴 맵을 컨트롤러에 보내고, 리턴 값으로 나온 모델을 받아온다.
        ModelView mv = controller.process(paramMap);

        //논리적인 이름 -> 실제 경로
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        //모델 뷰 안에 있는 모델을 넘겨줘야 한다.
        view.render(mv.getModel(),request,response);
    }

    //디테일한 로직이라, 메서드로 따로 뽑았다. (Ctrl Alt M)
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
