package syb.servlet;

import syb.dao.UserDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * this servlet is used to check the register information the user input whether right.
 * @author Lenovo
 */
@WebServlet(name = "UserRegisterCheckServlet", value = "/UserRegisterCheckServlet")
public class UserRegisterCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        //设置响应正文格式为test/xml
        response.setContentType("text/xml;charset=UTF-8");
        //禁止缓存
        response.addHeader("Cache-Control","no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control","post-check=0,pre-check=0");
        response.addHeader("Expires","0");
        response.addHeader("Pragma","no-cache");
        PrintWriter out = response.getWriter();
        out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        out.println("<userName>");
        //获取用户名
        String name = request.getParameter("name");
        //根据UTF-8将字符串解码
        name = java.net.URLDecoder.decode(name,"UTF-8");
        if(UserDAO.checkUser(name.trim()) != null){
            out.println("<iterance>" + name + "</iterance>");
        } else {
            out.println("<noIterance id=\"ok\"/>");
        }
        out.println("</userName>");
        out.close();
    }
}
