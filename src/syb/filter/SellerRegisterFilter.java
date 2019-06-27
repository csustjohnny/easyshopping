package syb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lenovo
 */
@WebFilter(filterName = "SellerRegisterFilter",value = {"/SellerRegisterServlet","/sellerregister.jsp",
"/GetUserImageServlet"})
public class SellerRegisterFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if(request.getSession().getAttribute("username")!=null){
            chain.doFilter(req,resp);
        }else {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public void init(FilterConfig config) {

    }

}
