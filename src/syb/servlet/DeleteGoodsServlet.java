package syb.servlet;

import syb.dao.GoodsDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Lenovo
 */
@WebServlet(name = "DeleteGoodsServlet", value="/deleteGoodsServlet")
public class DeleteGoodsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf8");
        String goodsNum = request.getParameter("goods_num");
        String sellerNum = request.getSession().getAttribute("username").toString();
        boolean result = false;
        try {
            result = GoodsDao.getInstance().deleteGoods(goodsNum,sellerNum);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(result) {
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
