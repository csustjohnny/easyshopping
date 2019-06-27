package syb.servlet;

import com.alibaba.fastjson.JSON;
import syb.dao.SellerDao;
import syb.model.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MyShopGoodsServlet", value = "/myShopGoodsServlet")
public class MyShopGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF8");
        response.setCharacterEncoding("UTF8");
        List<Goods> goodsList = null;
        try {
            goodsList = SellerDao.getInstance().getSellerGoods(request.getSession().getAttribute("username").toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String json = JSON.toJSONString(goodsList);
        response.getWriter().write(json);
    }
}
