<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="js/check.js" type="text/javascript"></script>
</head>
<body>
<form role="form" name="form" method="post" action="student/login" onsubmit="return checkEmpty(form);">
    <div class="form-group">
        <label for="exampleInputEmail1">学生学号登录：</label>
        <input type="text" class="form-control" id="exampleInputEmail1" name="studentId"/>
    </div>
    <button type="submit" class="btn btn-default">登&ensp;&ensp;&ensp;录</button>
</form>
</body>
</html>