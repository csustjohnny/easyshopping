<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/5/22
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <title>用户登录</title>
    <link href="css/register.css" rel="stylesheet">

    <script src="js/request.js"></script>
    <%--  easyUI依赖包--%>
    <jsp:include page="commonsupport.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end">

    <ul class="nav">
        <li class="nav-item mr-auto">
            <a class="nav-link" href="index.jsp">易购首页</a>
        </li>
       <%-- <li class="nav-item mr-auto">
            <a class="nav-link" href="login.jsp">注册</a>
        </li>--%>
        <li class="nav-item mr-auto">
            <a class="nav-link" href="#">联系客服</a>
        </li>
    </ul>
</nav>
<div style="margin-top: 5%; text-align: center">
    <h1 style="color: white;">易购登录</h1>
    <form action="${pageContext.request.contextPath}/LoginCheckServlet" style="text-align: center" method="post" id="registerForm">
        <table class="center_content" style="color: white">
            <tr>
                <td>用户账号：</td>
                <td><label for="nameID"></label><input type="text" name="nameID" id="nameID"></td>
                <td><span id="name_prompt"></span></td>
            </tr>

            <tr>
                <td>输入密码：</td>
                <td><label for="pwd"></label><input type="password" id="pwd" name="password"/></td>
                <td><span id="pwd_prompt"></span></td>
            </tr>

            <tr>
                <td>验证码：</td>
                <td>
                    <img alt="" src="${pageContext.request.contextPath}/ValidateCodeServlet" id="imgcode">
                    <%--<a href="javascript:change();">看不清，换一张</a>--%>
                </td>
            </tr>
            <tr>
                <td>输入验证码：</td>
                <td>
                    <label for="code"></label><input type="text" name="code" id="code">
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <input type="button" value="登  录"  onclick="CheckInput()"/>
                    <input type="button" value="注  册" onclick="register()"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="footer">
    <jsp:include page="foot.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
</div>
<script>
    var request = false;
    //切换验证码
    function change() {
        alert("change");
        var img = document.getElementById("imgcode");
        img.src = "/validateCodeServlet?";
    }
    //注册点击事件
    function register() {
        window.location.href = "register.jsp";
    }
    //登录验证事件
    function CheckInput() {
        var name = document.getElementById("nameID").value;
        var password = document.getElementById("pwd").value;
        var code = document.getElementById("code").value;
        var url = "LoginCheckServlet";
        var param = "name=" + name + "&password=" + password + "&code=" + code;
        if(name === "" || password===""){
            $.messager.alert("登录提示","用将用户名和密码填写完整","info");
        } else {
            request = httpRequest("post", url, false, callbackFunc, param);
        }

    }
    //ajax回调函数
    function callbackFunc() {
        if(request.readyState===4) {
            if (request.status === 200) {
                if(request.responseText === "true"){
                    $.messager.alert("登录提示","登录成功");
                    window.location.href = "index.jsp";
                } else if(request.responseText === "false") {
                    $.messager.alert("登录提示","用户名或密码错误，请检查后重试","info");
                } else {
                    $.messager.alert("登录提示",request.responseText,"info");
                }
            }
        }
    }
</script>
</body>
</html>
