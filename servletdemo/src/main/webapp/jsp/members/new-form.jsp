<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 25. 7. 10.
  Time: 오후 7:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
<%--  JSP는 서버 내부에서 서블릿으로 변환되는데,
우리가 만들었던 MemberFormServlet과 거의 비슷한 모습으로 변환된다  --%>
</form>
</body>
</html>
