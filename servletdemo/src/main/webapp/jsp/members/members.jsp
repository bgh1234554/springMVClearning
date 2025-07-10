<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 25. 7. 10.
  Time: 오후 7:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="hello.servletdemo.domain.member.MemberRepository" %>
<%@ page import="hello.servletdemo.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead> <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%--
    회원 리포지토리를 먼저 조회하고, 결과 List를 사용해서
    중간에 <tr><td> HTML 태그를 반복해서 출력하고 있다.
    --%>
    <%
        for (Member member : members) {
            out.write(" <tr>");
            out.write(" <td>" + member.getId() + "</td>");
            out.write(" <td>" + member.getUsername() + "</td>");
            out.write(" <td>" + member.getAge() + "</td>");
            out.write(" </tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>
