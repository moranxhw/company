<%@ page language="java" import="java.util.*,domain.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="css/login.css"/>
    <script src="js/check.js" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<% Student student1 = (Student) session.getAttribute("student"); %>
<form name="form" method="post" action="student/logout">
    <table class="table1">
        <tr class="yonghu">
            <td colspan="2">
                登录成功！<br>
                学生姓名为：
                <%=student1.getStudentName() %><br>
                学号为：
                <%=student1.getStudentId() %>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input class="dengluanniu" type="submit" value="学生退出"/>
            </td>
        </tr>
        <tr>
            <td>
                <br>
                <a href="borrow/borrow?action=jieshu">
                    <input class="jie1" type="button" value="点击借书"/>
                </a>
            </td>
            <td>
                <a href="borrow/borrow?action=huanshu">
                    <input class="jie2" type="button" value="已借书籍"/>
                </a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
