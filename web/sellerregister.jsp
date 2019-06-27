<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/5/28
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>卖家中心，上传商品</title>
    <link rel="stylesheet" href="css/uploadgoods.css">
    <script type="text/javascript" src="js/common.js"></script>
    <%--  easyUI依赖包--%>
    <jsp:include page="commonsupport.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
</head>
<body>
<%--顶部导航栏--%>
<nav class="nav" style="height: 56px; background: #eee; padding: 5px;">
    <ul class="nav" style="left: 0; position: absolute;" >
        <li class="nav-item">
            <em class="nav-link" >易购商家注册</em>
        </li>
    </ul>
    <ul class="nav " style="right: 0; position: absolute">
        <li class="nav-item">
            <a class="nav-link " href="#">联系客服</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="index.jsp">回到主页</a>
        </li>
        <%
            if (session.getAttribute("username") == null) {%>
        <%--用户未登录时显示--%>
        <li class="nav-item nav">
            <a class="nav-link" href="login.jsp">
                登录
            </a>
        </li>
        <%
            session.setAttribute("username",session.getAttribute("username"));
        } else {
        %>
        <%--用户已登录时显示--%>
        <li class="nav-item nav dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="index.jsp">
                你好,${sessionScope.username}
                <span class="caret"></span>
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="javascript:logout();">注销登录</a>
                <a class="dropdown-item" href="#">修改信息</a>
                <a class="dropdown-item" href="#">我的购物车</a>
            </div>
        </li>
        <%
            }
        %>
        <li class="nav-item mr-auto">
            <a class="nav-link" href="register.jsp">注册</a>
        </li>
    </ul>
</nav>
<%--<div id="page">
    <div class="col_sub">
        <div class="seller-sidebar">
            <div class="favorite">
                <span class="title">常用操作</span>
                <ul class="list">
                    <li>
                        <a target="_blank" href="#" >查看易购店铺</a>
                    </li>
                    <li>
                        <a href="#">店铺基本设置</a>
                    </li>
                    <li><a href="#">店铺基本设置</a> </li>
                    <li>
                        <a href="#">发布宝贝</a>
                    </li>
                </ul>
            </div>
            <div class="category-wrapper">
                <div class="category first">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a href="#">
                            <span class="title">交易管理</span>
                            <span>></span></a>
                        </li>
                        <li class="nav-item">
                            <a href="#">
                            <span class="title"></span>
                            <span>></span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>--%>
<div class="sell-card">
    <h2 class="sell-card-title">基础信息</h2>
    <div class="com-struct">
        <div class="sell-radio-top-row">
            <form action="SellerRegisterServlet" method="post" enctype="multipart/form-data" onsubmit="return registerCheck()">
                <table class="table">
                    <tr>
                        <td>
                            <label for="goods_name">商家名称： </label>
                            <input type="text" id="goods_name" name="shop_name" placeholder="输入您的店铺名称">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="img_logo">商家标志：</label>
                            <img src="image/defaultUser.jpeg" id="img_logo" height="100px" alt="logo"><br>
                            <input type="file" id="logo" accept="image/png,image/jpeg" name="shop_logo" style="margin-left: 10%">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>店铺类型：</label>
                            <input type="radio" id="individual_shop" name="shop_type" value="个人店铺">
                            <label for="individual_shop">个人店铺</label>
                            <input type="radio" id="enterprise_shop" name="shop_type" value="企业店铺">
                            <label for="enterprise_shop">企业店铺</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="shop_description">商店描述：</label>
                            <input type="text" id="shop_description" name="shop_description" placeholder="输入对您的店铺描述">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="address">商店地址：</label>
                            <input type="text" id="address" name="shop_address" placeholder="输入地址 中间用空格割开">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email">邮箱地址：</label>
                            <input type="email" id="email" name="email">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit">提交</button>
                        </td>
                    </tr>
                </table>
             </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#logo").change(function () {
            var $file = $(this);
            var fileObj = $file[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            var $img = $("#img_logo");

            if (fileObj && fileObj.files && fileObj.files[0]) {
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
                console.log(dataURL);
                $img.attr('src', dataURL);
            } else {
                dataURL = $file.val();
                var imgObj = document.getElementById("img_logo");
                imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

            }
        });
    });
    function registerCheck() {

        return true;
    }
</script>
</body>
</html>
