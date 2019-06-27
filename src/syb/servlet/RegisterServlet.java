package syb.servlet;

import syb.dao.UserDAO;
import syb.model.User;
import syb.tools.CommonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * this servlet is used to register a user.
 * @author Lenovo
 */
@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        response.setContentType("text/html; charset=utf8");
        String nameId = request.getParameter("nameID");
        String username = request.getParameter("username");
        String password = CommonUtils.MD5(request.getParameter("password"));
        System.out.println(password);
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        User user = new User();
        user.setUserID(nameId);
        user.setUserName(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setEmail(email);
        user.setAddress(address);
        if(UserDAO.addUser(user)){
            response.getWriter().write("添加成功<br>");

        } else {
            response.getWriter().write("添加失败<br>");
        }
        response.getWriter().write("\n将在3秒后跳转到登录界面");
        response.getWriter().write("\n若无法跳转请<a href=\"login.jsp\">点击此处</a>");
        response.setHeader("refresh","3; login.jsp");
    }

}
