<%@ page language="java" import="java.util.*,domain.*,java.text.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
    String dateStr = (String) session.getAttribute("dateStr");
%>
<table class="login">
    <tr>
        <td width="100%" align="middle" valign="bottom" height="30">
            用户“<font color="blue"><%=((Admin) session.getAttribute("user")).getUserName() %>
        </font>”登录成功
        </td>
    </tr>
    <tr>
        <td width="100%" height="30" align="middle" valign="middle">
            登录时间:<%=dateStr %>
            <br><a href="admin/logout">退出登录</a>
            <a href="pages/admin/AdminEdit.jsp">修改个人信息</a>
        </td>
    </tr>
</table>

</body>
</html>