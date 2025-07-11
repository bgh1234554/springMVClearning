package hello.servletdemo.web.frontcontroller.v5.adapter;

import hello.servletdemo.web.frontcontroller.ModelView;
import hello.servletdemo.web.frontcontroller.v4.ControllerV4;
import hello.servletdemo.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4)handler;

        //paramMap과 model을 만들어서 해당 컨트롤러를 호출 - 이걸 어댑터가 프론트 대신 해주는 것.
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        //V4는 paramMap과 model 2개가 필요하니까
        String viewName = controller.process(paramMap, model);

        /*
        어댑터가 호출하는 ControllerV4 는 뷰의 이름을 반환한다.
        그런데 어댑터는 뷰의 이름이 아니라 ModelView로 만들어서 반환해야 한다.
        ControllerV4 는 뷰의 이름을 반환했지만, 어댑터는 이것을 ModelView로 만들어서 형식을 맞추어 반환한다.
        이것이 어댑터가 필요한 이유이다!!
         */
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
}
