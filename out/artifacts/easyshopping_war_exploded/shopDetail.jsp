<%@ page import="syb.model.Goods" %>
<%@ page import="syb.dao.GoodsDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="syb.dao.CartDao" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/6/20
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%
        try {
            Goods goods = GoodsDao.getInstance().getGoodsById(request.getParameter("goods_number"));
            List<String> comments = CartDao.getInstance().getCommentsByGoodsId(goods.getGoodsNum().toString());
    %>
    <title><%=goods.getGoodsName()%>详情</title>
    <jsp:include page="commonsupport.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
    <style>
        .detail{
            margin-left: 20px;
            float: left;
            width: 300px;
        }
        .detail h1{
            padding-bottom: .2em;
            line-height: 1;
            font-size: 16px;
            font-weight: 700;
            color: #000;
        }
        .tm-ind-panel{
            border: 1px dotted #c9c9c9;
            border-right-width: 0;
            border-left-width: 0;
            margin: -1px 20px 0 0;
            padding: 10px 0;
            position: relative;
            overflow: hidden;
            clear: both;
            display: flex;
        }
        li, ul {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        .tm-ind-item {
            float: left;
            width: 33%;
            text-align: center;
            position: relative;
            left: -1px;
            border-left: 1px solid #e5dfda;
            flex: 1;
            line-height: 16px;

        }
    </style>
</head>
<body>
    <div class="container">

        <div style="width: 300px; float: left;">
            <img alt="暂无图像" src="image/goods/<%=goods.getGoodsPicture()%>" width="100%">
        </div>
        <div class="detail">
            <div><h1><%=goods.getGoodsName()%>&nbsp;&nbsp;<%=goods.getGoodsInfo()%></h1></div>
            <div>
                <h3>价格：<span id="price"><%=goods.getPrice()%></span></h3>
            </div>
            <div>
                <ul class="tm-ind-panel">
                    <li class="tm-ind-item"><span>库存：<%=goods.getInventory()%></span></li>
                    <li class="tm-ind-item"><span><a href="#">评论:<%=comments.size()%></a></span> </li>
                </ul>
            </div>
            <div>
                <label for="buyNumber"> 购买数量：</label><input id="buyNumber" type="number" value="1" min="1" max="<%=goods.getInventory()%>" >
            </div>
            <div class="alert alert-info">
                <strong>总价：</strong> <span id="totalPrice"><%=goods.getPrice()%></span>元
            </div>
            <div>
                <button class="btn btn-success" type="button" id="buy" goodsId="<%=goods.getGoodsNum()%>">购买</button>
            </div>
        </div>
        <%
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    %>

    </div>
<script type="text/javascript">
    $("#buyNumber").mouseleave(function(){
       $('#totalPrice').text($('#price').text() * $(this).val());
    });
    $('#buy').click(function(){
        var username = "<%=session.getAttribute("username")%>";
        var goodsNum = $('#buy').attr("goodsId");
        var number = $('#buyNumber').val();
        if(username.toString()==="null"){
            $.messager.alert("购买提示","请先登录");
            window.location.href = "login.jsp";
        } else {
            console.log(username);
            $.ajax({
               method:"post",
               url:"buyGoodsServlet",
               data:{
                    "goodsNum": goodsNum,
                    "number": number
               },
                dataType:"json",
                success: function(data){
                    $.messager.alert("购买提示",data);
                },
                error: function(){
                    $.messager.alert("购买提示","购买失败");
                }

            });
        }
    });
</script>
</body>
</html>
