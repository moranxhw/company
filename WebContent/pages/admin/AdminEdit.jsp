<%@ page language="java" import="java.util.*,domain.*,java.text.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书馆管理系统</title>
    <base href="<%=request.getContextPath() %>/"> <!-- 此例相当于<base href="/final/"> -->
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="./js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/index.css"/>
    <script src="js/check.js" type="text/javascript"></script>
</head>
<body>
<%
    String message = (String) request.getAttribute("message");
    Admin user = (Admin) session.getAttribute("user");
    if (user == null) {
%>
<script>
    alert("用户尚未登录");
    window.location.href = "pages/admin/login.jsp";
</script>
<%
    }
    if (message != null) {
%>
<script>
    alert("<%=message %>");
</script>
<%
    }
%>
<!---------------------- 此table展示查询结果 ---------------------------------------------------->
<div align="center" width="600px">
    <img src="img/tushuguanli.jpg" class="img-rounded" width="600px"/>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a>管理员信息修改</a>
                </li>
            </ul>
            <br/><br/>
        </div>
        <div class="col-md-2 column"></div>
        <div class="col-md-5 column">
            <form name="form" method="post" action="admin/edit" onsubmit="return checkForm(form);">
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" name="userName" value="<%=user.getUserName()%>"
                           readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" name="password1" id="password1"
                           value="<%=user.getPassword()%>"/>
                </div>
                <div class="form-group">
                    <label>确认密码</label>
                    <input type="password" class="form-control" name="password2" id="password2"/>
                </div>
                <div class="form-group">
                    <label>姓名</label>
                    <input type="text" class="form-control" name="realName" id="realName"
                           value="<%=user.getRealName()%>"/>
                </div>
                <div class="form-group">
                    <label>电话</label>
                    <input type="text" class="form-control" name="mobile" id="mobile" value="<%=user.getMobile()%>"/>
                </div>
                <div class="form-group">
                    <label>邮箱</label>
                    <input type="text" class="form-control" name="email" id="email" value="<%=user.getEmail()%>"/>
                </div>
                <div class="form-group">
                    <label>密保问题</label>
                    <input type="text" class="form-control" name="passwordQuestion" id="passwordQuestion"
                           value="<%=user.getPasswordQuestion()%>"/>
                </div>
                <div class="form-group">
                    <label>密保问题答案</label>
                    <input type="text" class="form-control" name="passwordHintAnswer" id="passwordHintAnswer"
                           value="<%=user.getPasswordHintAnswer()%>"/>
                </div>
                <div>
                    <button type="submit" value="submit" class="btn btn-default">提交</button>
                    &nbsp;&nbsp;
                    <a onclick="javascript:form.reset()">
                        <button type="button" class="btn btn-default">重置</button>
                    </a>
                    &nbsp;&nbsp;
                    <a onclick="javascript:history.go(-1)">
                        <button type="button" class="btn btn-default">返回</button>
                    </a>

                </div>
            </form>
        </div>
        <div class="col-md-5 column"></div>
    </div>
    <br><br><br>
</div>
</body>
</html>
