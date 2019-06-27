package syb.filter;

import syb.dao.SellerDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lenovo
 */
@WebFilter(filterName = "MyShopFilter",value = {"/myshop.jsp","/myShopGoodsServlet","/addGoodsServlet"})
public class MyShopFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Object username = request.getSession().getAttribute("username");
        if(username==null|| SellerDao.getInstance().checkSeller(username.toString())==null){
            response.sendRedirect("login.jsp");
        } else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig config) {

    }

}
