<%@ page language="java" import="java.util.*,domain.*,java.text.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新书入库</title>
    <base href="<%=request.getContextPath() %>/"> <!-- 此例相当于<base href="/final/"> -->
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="./js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/index.css"/>
    <script src="js/check.js" type="text/javascript"></script>
</head>
<body>
<%
    Admin user = (Admin) session.getAttribute("user");
    String message = (String) request.getAttribute("message");
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
<% } %>
<!---------------------- 此table展示查询结果 ---------------------------------------------------->
<div align="center" width="600px">
    <img src="img/tushuguanli.jpg" class="img-rounded" width="600px"/>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a>添加图书</a>
                </li>
            </ul>
            <br/><br/>
        </div>

        <div class="col-md-2 column"></div>
        <div class="col-md-5 column">
            <form name="form" method="post" action="book/add" onsubmit="return checkForm(form);">
                <div class="form-group">
                    <label>书籍名称</label>
                    <input type="text" class="form-control" name="bookName" id="bookName" required="required"/>
                </div>
                <div class="form-group">
                    <label>作者</label>
                    <input type="text" class="form-control" name="author" id="author" required="required"/>
                </div>
                <div class="form-group">
                    <label>书籍类别</label><br>
                    <select name="category" id="category" class="xialakuang">
                        <option value="小说">小说</option>
                        <option value="散文">散文</option>
                        <option value="故事集">故事集</option>
                        <option value="史记">史记</option>
                        <option value="教辅">教辅</option>
                        <option value="哲学">哲学</option>
                        <option value="自然科学">自然科学</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>出版社</label>
                    <input type="text" class="form-control" name="bookArea" id="bookArea" required="required"/>
                </div>
                <div class="form-group">
                    <label>入库数量</label>
                    <input type="text" class="form-control" name="bookNum" id="bookNum" required="required"/>
                </div>
                <div>
                    <button type="submit" value="submit" class="btn btn-default">提交</button>
                    &nbsp;&nbsp;
                    <a href="pages/bookmodify/addBook.jsp" onclick="javascript:form.reset()">
                        <button type="button" class="btn btn-default">重置</button>
                    </a>
                    &nbsp;&nbsp;
                    <a href="book/list?action=all" onclick="javascript:form.reset()">
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
