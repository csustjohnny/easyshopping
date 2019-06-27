package syb.servlet;

import syb.dao.UserDAO;
import syb.tools.CommonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * this servlet is used to check the information the user input whether valid.
 * @author Lenovo
 */
@WebServlet(name = "LoginCheckServlet", value = "/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //禁止缓存
        response.addHeader("Cache-Control","no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control","post-check=0,pre-check=0");
        response.addHeader("Expires","0");
        response.addHeader("Pragma","no-cache");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String password = CommonUtils.MD5(request.getParameter("password"));
        String code = request.getParameter("code");
        if(request.getSession().getAttribute("Code")==null){
            out.write("\"未知来源\"");
            out.close();
            return;
        }
        String realCode = request.getSession().getAttribute("Code").toString();
        if(!code.equals(realCode)){
            out.append("验证码不匹配");
            out.close();
            return ;
        }
        response.setContentType("application/json; charset=utf-8");
        if(UserDAO.checkPassword(name,password)){
            request.getSession().setAttribute("username",name);
            out.append("true");
        } else{
            out.append("false");
        }
    }
}
