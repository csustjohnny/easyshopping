<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/5/22
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<%--    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="css/register.css" rel="stylesheet">
<%--    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">--%>
    <title>账户注册</title>
    <jsp:include page="commonsupport.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
    <%--<script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>--%>
    <script src="js/request.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end">

    <ul class="nav">
        <li class="nav-item mr-auto">
            <a class="nav-link" href="index.jsp">易购首页</a>
        </li>
        <li class="nav-item mr-auto">
            <a class="nav-link" href="login.jsp">登录</a>
        </li>
        <li class="nav-item mr-auto">
            <a class="nav-link" href="#">联系客服</a>
        </li>
    </ul>
</nav>
<div style="text-align: center">
    <h1 style="color: white;">易购注册</h1>
    <form action="RegisterServlet" method="post" id="registerForm"  style="text-align: center;">
        <table class="center_content">
            <tr>
                <td>用户账号：</td>
                <td><label for="nameID"></label><input type="text" name="nameID" id="nameID" onblur="check()"></td>
                <td><span id="name_prompt"></span></td>
            </tr>
            <tr>
                <td>用户名称：</td>
                <td><label for="username"></label><input type="text" id="username" name="username"/></td>
            </tr>
            <tr>
                <td>设置密码：</td>
                <td><label for="pwd"></label><input type="password" id="pwd" name="password" onblur="checkPassword()"/></td>
                <td><span id="pwd_prompt"></span></td>
            </tr>
            <tr>
                <td>确认密码：</td>
                <td><label for="repwd"></label><input type="password" id="repwd" name="repassword" onblur="checkRePassword()" /></td>
                <td><span id="repwd_prompt"></span></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <label>
                        <input type="radio" name="sex" value="男" />
                    </label>男
                    <label>
                        <input type="radio" name="sex" value="女" />
                    </label>女
                </td>
            </tr>
            <tr>
                <td>Email：</td>
                <td><label>
                    <input type="email" name="email" />
                </label></td>
            </tr>
            <tr>
                <td>住址：</td>
                <td><label>
                    <input type="text" name="address" />
                </label></td>
            </tr>
            <tr>
                <td>验证码：</td>
                <td>
                    <img alt="" src="${pageContext.request.contextPath}/ValidateCodeServlet" >
                </td>
            </tr>
            <tr>
                <td>输入验证码：</td>
                <td>
                    <label for="code"></label><input type="text" name="code" id="code"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="below_button">
                    <input type="button" value="注 册"  onclick="CheckInput()"/>
                    <input type="reset" value="重 置" />
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
    var isIdValidate = false;
    var isPasswordValid = false;
    var isRePasswordValid = false;
    var isCodeRight = false;
    //检查验证码
    function CheckInput() {
        //alert(isIdValidate + '' + isPasswordValid + '' + isRePasswordValid);
        var code = document.getElementById("code").value;
        if(code === null || code ===''){
            alert("请填写验证码");
            isCodeRight = false;
            return ;
        }
        var url = "CheckCodeServlet";
        var param = "code=" + code;
        request = httpRequest("post", url, false, checkCode, param);

    }
    function checkCode() {
        if(request.readyState === 4) {
            if (request.status === 200) {
                var codeValidate = request.responseText;
                console.log(codeValidate);
                if(codeValidate==="true"){
                    isCodeRight = true;
                    console.log("isCodeRight: " + isCodeRight);
                } else {
                    isCodeRight = false;
                    console.log("isCodeRight: " + isCodeRight);
                }
                if(isCodeRight && isRePasswordValid && isPasswordValid && isIdValidate)
                    document.getElementById("registerForm").submit();
                else {
                    alert("请按照要求填完所有选项");
                }
            }
        }
    }
    //检查密码是否合法
    function checkPassword() {
        var reg=/^[a-zA-Z0-9]{4,10}$/;
        var password = document.getElementById('pwd').value;
        if(reg.test(password) === true){
            document.getElementById('pwd_prompt').innerHTML = '';
            isPasswordValid = true;
        } else {
            document.getElementById('pwd_prompt').style.color = 'red';
            document.getElementById('pwd_prompt').innerHTML = "密码需为4-10位字符(字母和数字)";
            isPasswordValid = false;
        }
    }
    //检查密码输入是否一致
    function checkRePassword() {
        var pwd = document.getElementById("pwd").value;
        var repwd = document.getElementById("repwd").value;
        if(repwd === '' || repwd == null)
            return false;
        if(pwd !== repwd){
            document.getElementById("repwd_prompt").style.color = "red";

            document.getElementById("repwd_prompt").innerHTML = "两次密码不匹配";
        } else {
            document.getElementById("repwd_prompt").innerHTML = '';
            document.getElementById("repwd_prompt").style.color = "black";
            isRePasswordValid = true;
        }
    }
    //注册名称验证
    function check() {
        var name = document.getElementById('nameID').value;
        if(name==null || name===''){
            alert("请输入账号后重试");
            document.getElementById('nameID').focus();
            request = false;
        }
        else {
            var url = "UserRegisterCheckServlet";
            var param = "name=" + name;
            //调用Ajax提交请求方法
            request = httpRequest("post", url, true, callbackFunc, param);
        }
    }
    //响应的回调函数
    function callbackFunc() {
        if(request.readyState === 4){
            if(request.status === 200){
                //获得从服务器返回的xml信息，noIterance为xml中的元素
                var noIterance = request.responseXML.getElementsByTagName("noIterance");
                //获得从服务器端返回的XML信息，iterance为xml中的元素
                var iterance = request.responseXML.getElementsByTagName("iterance");
                if(typeof(noIterance)!="undefined" && noIterance.length>0){
                    document.getElementById('name_prompt').style.color='green';
                    document.getElementById("name_prompt").innerHTML='用户名合法';
                    request = false;
                    isIdValidate = true;
                    return true;
                }
                if(typeof(iterance)!="undefined" && iterance.length>0){
                    document.getElementById('name_prompt').style.color='red';
                    document.getElementById("name_prompt").innerHTML='用户名已存在，请重新输入';
                    document.getElementById("nameID").focus();
                    isIdValidate = false;
                    request = false;
                }
            }
        }
    }
</script>
</body>
</html>
