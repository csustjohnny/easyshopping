package syb.servlet;

import com.alibaba.fastjson.JSON;
import syb.dao.GoodsDao;
import syb.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BuyGoodsServlet",value = "/buyGoodsServlet")
public class BuyGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html,charset=UTF-8");
        String usernum = request.getSession().getAttribute("username").toString();
        int number = Integer.parseInt(request.getParameter("number"));
        String goodsNum = request.getParameter("goodsNum");
        String reply = "";
        try {
            reply = UserDAO.buyGoods(goodsNum,number,usernum);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.getWriter().write(JSON.toJSONString(reply));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
