package hello.servletdemo.web.frontcontroller.v5;

import hello.servletdemo.web.frontcontroller.ModelView;
import hello.servletdemo.web.frontcontroller.MyView;

import hello.servletdemo.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servletdemo.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servletdemo.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servletdemo.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servletdemo.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servletdemo.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servletdemo.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servletdemo.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//하위 모든 요청은 이 서블릿에서 받아들인다.
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //매핑 정보의 값이 ControllerV3 , ControllerV4 같은 인터페이스에서 아무 값이나 받을 수 있는 Object 로 변경되었다.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5(){
        initHandlerMappingMap(); //핸들러 매핑 초기화
        initHandlerAdapters(); //어댑터 초기화
    }

    //지원하는 어댑터
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter()); //V4 추가
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new
                MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new
                MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new
                MemberListControllerV3());

        //ControllerV4 형식도 지원하게 만들어보기
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new
                MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new
                MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new
                MemberListControllerV4());
        //V3에서 V4로 기능을 확장할 때, 추가하는 코드가 거의 없다. 깔끔하다.
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //해당 URI 담당 핸들러를 호출한다.
        Object handler = getHandler(request);
        if (handler != null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //핸들러에 맞는 어댑터를 찾는다 (단순 for each 문)
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        //해당 어댑터가 핸들러의 로직을 실행시켜 모델뷰에 담아온다.
        ModelView mv = adapter.handle(request, response, handler);

        //모델 뷰 이름에 맞는 view를 모델안에 들어있는 속성을 보고 생성한다.
        MyView view = viewResolver(mv.getViewName());
        //이후 뷰를 화면에 렌더링하기 위해 JSP를 호출하는 로직을 불러온다.
        view.render(mv.getModel(),request,response);
        //구현 클래스가 뭐인지 상관이 없다.
    }

    //해당 URI가 어떤 핸들러 담당인지 호출
    private Object getHandler(HttpServletRequest request) {
        String reqeustURI = request.getRequestURI();
        return handlerMappingMap.get(reqeustURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for(MyHandlerAdapter adapter : handlerAdapters){
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("Failed to find suitable handler adapter for "+handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
