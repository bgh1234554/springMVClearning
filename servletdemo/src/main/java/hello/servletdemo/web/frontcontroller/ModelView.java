package hello.servletdemo.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<String, Object>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
/*
서블릿 종속성 제거하기 - V3

컨트롤러에서 서블릿 기술을 몰라도 로직을 구현할 수 있도록 수정하기.

request 객체 대신에 별도의 Model 객체를 만들어 거기에 내용을 담아서 보낸다.

컨트롤러는 뷰의 논리 이름을 반환하고, 실제 물리 위치의 이름은 프론트 컨트롤러에서
처리하도록 단순화할 수 있다.

ModelView 객체가 별도의 Model 역할을 수행하기 위해서 탄생하였다.
 */