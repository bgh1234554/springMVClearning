package hello.servletdemo.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component("/springmvc/old-controller") //스프링 빈의 이름을 URL 패턴으로 지정
public class OldController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
        //논리적인 이름만 넣었다. 뷰를 못찾아서 오류 페이지가 나온다.
        //ViewResolver을 만들어줘야 한다. -> applications.properties 수정
    }
}
