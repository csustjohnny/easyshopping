<%@ page import="syb.dao.SellerDao" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/6/5
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>易购网-我的购物车</title>
    <jsp:include page="commonsupport.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
</head>
<body>
<nav class="nav" style="height: 56px; background: #eee; padding: 5px;">
    <ul class="nav" style="left: 0; position: absolute;">
        <li class="nav-item">
            <em class="nav-link"></em>
        </li>
    </ul>
    <ul class="nav" style="right: 0; position: absolute">
        <%--<li class="nav-item">
            <a class="nav-link " href="#">联系客服 </a>
        </li>--%>

        <%
            if (session.getAttribute("username") == null) {%>
        <%--用户未登录时显示--%>
        <li class="nav-item nav">
            <a class="nav-link" href="login.jsp">
                登录
            </a>
        </li>
        <%
            session.setAttribute("username", session.getAttribute("username"));
        } else {
        %>
        <%--用户已登录时显示--%>
        <%
            if (SellerDao.getInstance().checkSeller(session.getAttribute("username").toString()) != null) {
        %>
        <li class="nav-item nav">
            <a href="myshop.jsp" class="nav-link">我的店铺</a>
        </li>
        <%
        } else {
        %>
        <li class="nav-item nav">
            <a href="sellerregister.jsp" class="nav-link">我要开店</a>
        </li>
        <%
            }
        %>
        <li class="nav-item nav">
            <div class="dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
                    你好,${sessionScope.username}
                    <span class="caret"></span>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="javascript:logout();">注销登录</a>
                    <a class="dropdown-item" href="#">修改信息</a>
                    <a class="dropdown-item" href="cart.jsp">我的购物车</a>
                </div>
            </div>
        </li>
        <%
            }
        %>
        <%--        <li class="nav-item nav">--%>
        <%--            --%>
        <%--        </li>--%>
        <li class="nav-item mr-auto">
            <a class="nav-link" href="register.jsp">注册</a>
        </li>
    </ul>
</nav>
</body>
</html>
