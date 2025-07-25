package hello.servletdemo.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//회원 등록 폼 서블릿
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다.
        String viewPath = "/WEB-INF/jsp/members/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); // 다른 서블릿이나 JSP로 이동
    }
}
/*
redirect vs forward
리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가,
클라이언트가 redirect 경로로 다시 요청한다.
따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다.
반면에 포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다
 */