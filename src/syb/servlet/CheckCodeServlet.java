package syb.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * this servlet is used to check the identifying code whether right.
 * @author Lenovo
 */
@WebServlet(name = "CheckCodeServlet",value = "/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String vcode = request.getParameter("code");
        String realCode = request.getSession().getAttribute("Code").toString();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        //获取用户名
        //String name = request.getParameter("name");
        //根据UTF-8将字符串解码
        //name = java.net.URLDecoder.decode(name,"UTF-8");
        if(!vcode.equals(realCode)){
            out.append("false");
        } else {
            out.append("true");
        }
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
