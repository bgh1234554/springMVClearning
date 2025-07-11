package hello.servletdemo.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     *
     * @param paramMap
     * @param model
     * @return
     */
    //파라미터에 모델까지 다 만들어서 넘겨주면, 개발자는 그 모델에 데이터를 담기만 하면 된다.
    //리턴할 때는 뷰의 이름만 반환하면 된다.
    String process(Map<String,String> paramMap, Map<String,Object> model);
}
/*
실제 ModelView 객체를 생성하지 않아도 되게 실용성을 높여보자.
 */
