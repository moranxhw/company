<%@ page language="java" import="domain.*,dao.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书馆管理系统</title>
    <base href="<%=request.getContextPath() %>/">
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="./js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/zhaohui.css"/>
    <script src="js/check.js" type="text/javascript"></script>
</head>
<body>
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
%>
<script>
    alert("<%=message %>");
</script>
<%
    }
%>
<table>
    <tr>
        <td>
            <div class="biaotou"><img src="img/logo.png" width="950px" height="120px"/></div>
            <div class="tushu"><img src="img/tushuguanli.jpg" width="400px"></div>
        </td>
    </tr>
    <tr>
        <td>
            <div class="jindu"><img src="img/xgmm02.jpg" width="1000px"></div>
        </td>
    </tr>
    <tr>
        <td>
            <div>
                <form name="login" method="POST" action="admin/passwordFind?action=checkAnswer"
                      onsubmit="return checkEmpty(login);">
                    <input name="userName" type="hidden" class="form-control"
                           value="<%=((Admin)session.getAttribute("user")).getUserName()%>"/>
                    <table class="table1">
                        <tr height="50px"></tr>
                        <tr class="yonghu">
                            <td>密保问题：</td>
                            <td><%=((Admin) session.getAttribute("user")).getPasswordQuestion() %>
                            </td>
                        </tr>
                        <tr class="yonghu">
                            <td>请输入答案：</td>
                            <td><input name="answer" type="text" class="form-control" required="required"/></td>
                        </tr>
                        <tr height="30px"></tr>
                        <tr>
                            <td colspan="2">
                                <div>
                                    <button type="submit" value="submit" class="btn btn-default">下一步</button>
                                    &nbsp;&nbsp;
                                    <a href="pages/passwordFind/passwordFind_answer.jsp"
                                       onclick="javascript:form.reset()">
                                        <button type="button" class="btn btn-default">重置</button>
                                    </a>
                                    &nbsp;&nbsp;
                                    <a href="pages/passwordFind/passwordFind_name.jsp"
                                       onclick="javascript:history.go(-1)">
                                        <button type="button" class="btn btn-default">返回</button>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </td>
    </tr>
    <tr>
        <td align="center">
            <div class="copyright">
                <p>Copyright © 2020 南京信息职业技术学院 </p>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
