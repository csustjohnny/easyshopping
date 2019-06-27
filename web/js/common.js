function logout() {
    $.messager.confirm('注销提示！', '您确定要注销登录吗？', function (r) {
        if (r) {
            $.messager.alert("注销成功","您以成功注销");
            window.location.href = "/logoutServlet";
        }
    });
}