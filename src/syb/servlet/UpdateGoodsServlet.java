package syb.servlet;

import syb.dao.GoodsDao;
import syb.model.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

@WebServlet(name = "UpdateGoodsServlet", value = "/updateGoodsServlet")
public class UpdateGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        response.setContentType("text/html; charset=utf8");
        Goods goods = new Goods();
        goods.setGoodsNum(BigInteger.valueOf(Long.parseLong(request.getParameter("goods_num"))));
        goods.setGoodsName(request.getParameter("goods_name"));
        goods.setGoodsType(request.getParameter("goods_type"));
        goods.setGoodsInfo(request.getParameter("goods_info"));
        goods.setInventory(Integer.parseInt(request.getParameter("inventory")));
        goods.setPrice(Double.parseDouble(request.getParameter("price")));
        boolean result = false;
        try {
            result = GoodsDao.getInstance().updateGoods(goods);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(result){
            response.getWriter().write("更新成功");
        } else {
            response.getWriter().write("更新失败");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
