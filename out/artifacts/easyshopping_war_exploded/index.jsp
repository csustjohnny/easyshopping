<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="java.util.*" %>
<%@ page import="syb.dao.GoodsDao" %>
<%@ page import="syb.model.Goods" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no;" name="viewport" />
    <title>易购网首页</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <jsp:include page="commonsupport.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
    <style type="text/css">

    </style>
</head>
<body style="min-width: 770px">
<%--the top navigation--%>
<jsp:include page="head.jsp">
    <jsp:param name="" value=""/>
</jsp:include>
<%--the shop logo--%>
<div class="logo">
    <h1>
        <a href="index.jsp"><img src="image/易购.jpg" alt="" width="150px"></a>
    </h1>
</div>
<%--search box--%>
<div class="search d3">
    <form class="search_form">
        <label>
            <input type="text" placeholder="搜索您想要的商品...">
        </label>
        <button type="button"></button>
    </form>
</div>
<div class="goods_Info">

    <div class="row" style="width: 1210px; margin: 0 auto;">
    <%
        List<Goods> productList = GoodsDao.getInstance().getAllGoods();
        for(Goods goods : productList){
    %>
        <div class="col-md-2" style="height:250px">
            <a href="shopDetail.jsp?goods_number=<%=goods.getGoodsNum()%>" target="_blank"> <img src="${pageContext.request.contextPath }/image/goods/<%=goods.getGoodsPicture()%>"
                                             width="170" height="170" style="display: inline-block;">
            </a>
            <p>
                <a href="shopDetail.jsp?goods_number=<%=goods.getGoodsNum()%>" style='color: green' target="_blank"><%=goods.getGoodsName()%></a>
            </p>
            <p>
                <span style="color: #FF0000; ">商城价：¥<%=goods.getPrice()%></span>
            </p>
    </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>