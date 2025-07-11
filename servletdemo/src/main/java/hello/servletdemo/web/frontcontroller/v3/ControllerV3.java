package hello.servletdemo.web.frontcontroller.v3;

import hello.servletdemo.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    //서블릿 관련 기술이 전혀 없다. 응답 결과로 Model 데이터를 포함하는 ModelView 객체를 반환한다.
    ModelView process(Map<String,String> paramMap);
}
