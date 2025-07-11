package hello.servletdemo.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    //V3 버전에서 추가됨
    public void render(Map<String, Object> model, HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    //모델의 데이터를 꺼내서 request.setAttribute로 담기
    private void modelToRequestAttribute(Map<String, Object> model,
                                         HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
//        model.forEach(request::setAttribute); //메서드 참조 사용하는 방법
    }
}
/*
모든 컨트롤러에서 뷰로 이동하는 부분에 dispatcher을 생성하는 부분이 있으므로,
하나로 합친다.
 */
