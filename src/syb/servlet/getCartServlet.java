package syb.servlet;

import com.alibaba.fastjson.JSON;
import syb.dao.CartDao;
import syb.model.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getCartServlet", value = "/getCartServlet")
public class getCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerNum = request.getSession().getAttribute("username").toString();
        List<ShoppingCart> list = CartDao.getInstance().getCartList(customerNum);
        System.out.println(JSON.toJSONString(list));
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(list));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
