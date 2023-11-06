<%@ page language="java" import="java.util.*,domain.*,java.text.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学生信息修改</title>
    <base href="<%=request.getContextPath() %>/"> <!-- 此例相当于<base href="/final/"> -->
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="./js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/index.css"/>
    <script src="js/check.js" type="text/javascript"></script>
</head>
<body>
<%
    Student student = (Student) request.getAttribute("student");
    String message = (String) request.getAttribute("message");
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
                    <a>学生信息修改</a>
                </li>
            </ul>
            <br/><br/>
        </div>
        <div class="col-md-2 column"></div>
        <div class="col-md-5 column">
            <form name="form" method="post" action="student/modify" onsubmit="return checkForm(form);">
                <div class="form-group">
                    <label>学号</label>
                    <input type="text" class="form-control" name="studentId" value="<%=student.getStudentId()%>"
                           readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label>姓名</label>
                    <input type="text" class="form-control" name="studentName" value="<%=student.getStudentName()%>"/>
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <select class="form-control" name="sex">
                        <% if ("男".equals(student.getSex())) { %>
                        <option value="男" selected="selected">男</option>
                        <option value="女">女</option>
                        <% } else { %>
                        <option value="男">男</option>
                        <option value="女" selected="selected">女</option>
                        <% } %>
                    </select>
                    <%-- <input type="text" class="form-control" name="sex" value="<%=student.getSex()%>" /> --%>
                </div>
                <div class="form-group">
                    <label>电话</label>
                    <input type="text" class="form-control" name="tel" value="<%=student.getTel()%>"/>
                </div>
                <div class="form-group">
                    <label>入学年份</label>
                    <input type="text" class="form-control" name="enrolldate" value="<%=student.getEnrolldate()%>"/>
                </div>
                <div class="form-group">
                    <label>班级</label>
                    <input type="text" class="form-control" name="classid" value="<%=student.getClassid()%>"/>
                </div>
                <div class="form-group">
                    <label>分院</label>
                    <input type="text" class="form-control" name="college" value="<%=student.getCollege()%>"/>
                </div>
                <div>
                    <button type="submit" value="submit" class="btn btn-default">提交</button>
                    &nbsp;&nbsp;
                    <a onclick="javascript:form.reset()">
                        <button type="button" class="btn btn-default">重置</button>
                    </a>
                    &nbsp;&nbsp;
                    <a href="student/show?action=all" onclick="javascript:form.reset()">
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
