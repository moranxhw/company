<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书馆管理系统</title>
    <base href="<%=request.getContextPath() %>/"> <!-- 此例相当于<base href="/final/"> -->
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="./js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/login.css"/>
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
            <div class="biaotou"><img src="img/tushuguanli.jpg" width="60%" height="120px"/></div>
            
        </td>
    </tr>
    <tr>
        <td>
            <div class="biaozhong"><img src="img/banner02.jpg" width="1516px" height="640px"></div>
            <div class="denglu">
                <form name="login" method="POST" action="admin/login" onsubmit="return checkEmpty(login);">
                    <table class="table1">
                        <tr height="150px">
                            <td class="guanliyuan" colspan="2">管理员登录</td>
                        </tr>
                        <tr class="yonghu">
                            <td>用户名：</td>
                            <td><input name="AdminsuserName" type="text" class="form-control"/></td>
                        </tr>
                        <tr class="yonghu">
                            <td>密&ensp;&ensp;码：</td>
                            <td><input name="Adminspassword" type="password" class="form-control"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input class="dengluanniu" type="submit"
                                       value="登&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;录"/>
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
                <p>Copyright © 2020 南京邮电大学 </p>
            </div>
        </td>
    </tr>
</table>
</body>
</html>