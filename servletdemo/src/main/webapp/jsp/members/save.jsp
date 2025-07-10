<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 25. 7. 10.
  Time: 오후 7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="hello.servletdemo.domain.member.MemberRepository" %>
<%@ page import="hello.servletdemo.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response는 알아서 사용 가능 (Servlet이 자동으로 호출되기 때문에 문법상 지원
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("save.jsp");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
    //JSP는 서블릿과 코드와 똑같으나, JSP는 HTML이 중심하는거임
%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
