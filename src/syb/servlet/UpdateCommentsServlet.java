package syb.servlet;

import com.alibaba.fastjson.JSON;
import syb.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateCommentsServlet",value = "/updateCommentsServlet")
public class UpdateCommentsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("cartId"));
        String comments = request.getParameter("comments");
        CartDao.getInstance().updateComments(id,comments);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString("修改成功"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
