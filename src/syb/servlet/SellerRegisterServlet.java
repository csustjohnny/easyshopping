package syb.servlet;

import syb.dao.SellerDao;
import syb.model.Seller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

import static syb.tools.CommonUtils.MAX_SIZE;
import static syb.tools.CommonUtils.inputFile;

@WebServlet(name = "SellerRegisterServlet", value = "/SellerRegisterServlet")
@MultipartConfig
public class SellerRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Seller seller = new Seller();
        seller.setSellerNum(request.getSession().getAttribute("username").toString());
        seller.setShopName(request.getParameter("shop_name"));
        seller.setShopType(request.getParameter("shop_type"));
        seller.setShopDescription(request.getParameter("shop_description"));
        seller.setAddress(request.getParameter("shop_address"));
        seller.setEmail(request.getParameter("email"));
        Part part = request.getPart("shop_logo");
        System.out.println(part.getName());
        String inputName = part.getSubmittedFileName();
        String suffix = inputName.substring(inputName.lastIndexOf('.')+1);
        seller.setShopLogo(seller.getSellerNum()+""+seller.getShopName() + "." + suffix);
        System.out.println(part.getSize());
        if(part.getSize()>MAX_SIZE){
            String lastURL = request.getHeader("Referer");
            response.getWriter().write("<script language='javascript'>alert('传入照片过大!')</script>");
            response.getWriter().write("<script>window.location.href='"+ lastURL +"'</script>");
        } else {
            InputStream inputStream = part.getInputStream();
            //想要保存的文件目录下
            String realPath = this.getServletContext().getRealPath("/image/user/");
            inputFile(inputStream, realPath, seller.getShopLogo());
            SellerDao.getInstance().addSeller(seller);
            response.getWriter().println("注册商家成功!将在3秒后返回到主页");
            response.getWriter().write("\n若无法跳转请<a href=\"index.jsp\">点击此处</a>");
            response.setHeader("refresh","3; index.jsp");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
