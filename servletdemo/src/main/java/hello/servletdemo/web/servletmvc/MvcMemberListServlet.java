package hello.servletdemo.web.servletmvc;

import hello.servletdemo.domain.member.Member;
import hello.servletdemo.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//회원 목록 조회 - 컨트롤러
@WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        System.out.println("MvcMemberListServlet.service");
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);
        String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
/*
현재까지의 MVC의 한계
1. 포워드 중복
 * View로 이동하는 코드가 항상 중복 호출되어야 한다. 물론 이 부분을 메서드로 공통화해도 되지만, 해당 메서드도 항상
    직접 호출해야 한다.

RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
dispatcher.forward(request, response);

2. ViewPath에 중복
String viewPath = "/WEB-INF/views/new-form.jsp";

prefix: /WEB-INF/views/
suffix: .jsp

그리고 만약 jsp가 아닌 thymeleaf 같은 다른 뷰로 변경한다면 전체 코드를 다 변경해야 한다.

3. 사용하지 않는 코드
HttpServletRequest request, HttpServletResponse response

 * response는 현재 코드에서 사용되지 않는다. 이런 코드는 테스트케이스를 작성하기도 어렵다.

4. 공통 처리가 어렵다.
기능이 증가할 수록, 컨트롤러에서 공통으로 처리해야 하는 부분이 점점 더 많이 증가할 것
-> 해당 기능을 항상 호출해야 하기 때문에 중복이 발생
-> 수문장 역할을 하는 Front Controller 도입 -> Spring MVC의 핵심

 */