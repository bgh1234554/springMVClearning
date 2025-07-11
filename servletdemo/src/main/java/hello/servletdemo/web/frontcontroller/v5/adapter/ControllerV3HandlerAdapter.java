package hello.servletdemo.web.frontcontroller.v5.adapter;

import hello.servletdemo.web.frontcontroller.ModelView;
import hello.servletdemo.web.frontcontroller.v3.ControllerV3;
import hello.servletdemo.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //handler를 controllerV3로 변환후 V3 형식에 맞도록 호출
        //front controller에서 supports를 호출해 확인하기 때문에 그냥 캐스팅해도 된다.
        ControllerV3 controller = (ControllerV3)handler;

        //V3 버전의 컨트롤러를 사용할 수 있다.
        Map<String,String> paramMap=createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv; //반환 타입을 ModelView로 맞춰줘야 한다.
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<String, String>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->
                        paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
