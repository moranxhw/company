<%@ page language="java" import="java.util.*,domain.*,java.text.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书馆管理系统</title>
    <base href="<%=request.getContextPath() %>/">
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="./js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/index.css"/>
    <script src="js/check.js" type="text/javascript"></script>

</head>
<body>
<%
    Admin admin = (Admin) session.getAttribute("user");
    if (admin == null) {
%>
<script>
    alert("您尚未登录，请先登录！");
    window.location.href = "../../pages/admin/login.jsp";
</script>
<%
    }
    List<Borrow> borrowList = (List<Borrow>) request.getAttribute("borrowList");
    String action = request.getParameter("action");//取得请求参数中的action值，用于判断当前是什么操作
    String keywords = request.getParameter("keywords");
    int start = (Integer) request.getAttribute("start");//起始记录号
    int end = (Integer) request.getAttribute("end");//结束记录号
    int maxPageNum = (Integer) request.getAttribute("maxPageNum");//总页数(最大页号)
    int totalRecords = (Integer) request.getAttribute("totalRecords");//查询出的商品总数，总记录数
    int pageNum = (Integer) request.getAttribute("pageNum");//整型页号，可以用于计算
    int tiaozhuan = 0;//跳转的页面

%>
<!---------------------- 此table展示查询结果 ---------------------------------------------------->
<div align="center" width="600px">
    <img src="img/tushuguanli.jpg" class="img-rounded" width="600px"/>
</div>
<div class="youshang">
    <%@include file="topright/showLogin.jsp" %>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="nav nav-tabs">
                <li>
                    <a href="book/list?action=all">图书查询</a>
                </li>
                <li>
                    <a href="borrow/borrow?action=jieshu">书籍借阅</a>
                </li>
                <li class="active">
                    <a href="borrow/list?action=borrowList">借书记录</a>
                </li>
                <li class="xiala">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">更多<strong class="caret"></strong></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="pages/bookmodify/addBook.jsp">新书入库</a>
                        </li>
                        <li>
                            <a href="book/showNum?action=all">书籍管理</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="student/show?action=all">查找学生</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown pull-right">
                    <form class="sousuo" name="searchForm" method="POST" action="borrow/list?action=search"
                          onsubmit="return checkEmpty(searchForm);">
                        <input name="keywords" placeholder="请输入搜索内容" type="text" size="22">
                        <input type="submit" value="搜索"/>
                    </form>
                </li>
                <li class="weiguihuan">
                    <form method="POST" action="borrow/list?action=noReturn">
                        <input type="submit" value="查看未归还图书"/>
                    </form>
                </li>
            </ul>
            <table class="table table-striped table-hover" id="mytable">
                <thead>
                <tr class="success">
                    <th width="80px">编号</th>
                    <th width="130px">学生学号</th>
                    <th width="110px">学生姓名</th>
                    <th width="150px">书籍编号</th>
                    <th width="140px">书名</th>
                    <th width="250px">借书时间</th>
                    <th width="250px">还书时间</th>
                    <th width="120px">联系学生</th>
                </tr>
                </thead>
                <tbody>
                <% if (borrowList.size() == 0) { %>
                <tr>
                    <td colspan="2">
                        <font color="red" size="5">对不起，找不到您要的记录</font>
                    </td>
                </tr>
                <%
                } else {
                    for (int i = start; i < end; i++) {
                        Borrow borrow = borrowList.get(i);
                %>
                <tr>
                    <td><%=borrow.getMainId() %>
                    </td>
                    <td><%=borrow.getStudentId() %>
                    </td>
                    <td><%=borrow.getStudentName() %>
                    </td>
                    <td><%=borrow.getId() %>
                    </td>
                    <td><%=borrow.getBookName() %>
                    </td>
                    <td><%=borrow.getCreatetime() %>
                    </td>
                    <td>
                        <% if (borrow.getReturntime() == null) { %>
                        <font color="red">尚未归还</font>
                        <% } else { %>
                        <%=borrow.getReturntime() %>
                        <% } %>
                    </td>
                    <td>
                        <form name="searchForm" method="POST" action="student/show?action=search">
                            <input type="hidden" name="keywords" value=<%=borrow.getStudentId()%>></input>
                            <input type="submit" value="联系学生"/>
                        </form>
                    </td>
                </tr>
                <% }
                } %>
                <tr>  <!-- 分页导航条开始，上一页，下一页等超链接 -->
                    <td colspan="7">
                        <form id="pageSizeForm" method="post">
                            <table width="1200">
                                <tr>
                                    <td>共<%=maxPageNum %>页</td>
                                    <td>共<%=totalRecords %>条记录</td>
                                    <td>当前为第<%=pageNum %>页</td>
                                    <td>点击跳转到第
                                        <select onchange="self.location.href=options[selectedIndex].value">
                                            <option value="null" selected="selected"></option><!-- 下拉框默认为空 -->
                                            <% for (tiaozhuan = 1; tiaozhuan <= maxPageNum; tiaozhuan++) { %>
                                            <option value="borrow/list?pageNum=<%=tiaozhuan %>&keywords=<%=keywords %>&action=<%=action %>"><%=tiaozhuan %>
                                            </option>
                                            <% } %>
                                        </select>
                                        页
                                    </td>
                                    <% if(pageNum > 1){ %><!-- 如果当前页号大于1，显示“上一页”超链接 -->
                                    <td>
                                        <a href="borrow/list?pageNum=<%=pageNum-1 %>&keywords=<%=keywords %>&action=<%=action %>"><input
                                                type="button" value="上一页"></a></td>
                                    <% }
													if(pageNum < maxPageNum){
												%><!-- 如果当前页号小于最大页号，显示“下一页”超链接 -->
                                    <td>
                                        <a href="borrow/list?pageNum=<%=pageNum+1 %>&keywords=<%=keywords %>&action=<%=action %>"><input
                                                type="button" value="下一页"></a></td>
                                    <% } %>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
