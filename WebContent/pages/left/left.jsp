<%@ page language="java" import="java.util.*,domain.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
<div class="jumbotron">
    <%
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
    %>
    <%@include file="login.jsp" %>
    <%
    } else {
    %>
    <%@include file="successLogin.jsp" %>
    <%
        }
    %>
</div>
</body>
</html>
