package syb.servlet;

import syb.dao.GoodsDao;
import syb.model.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;

import static syb.tools.CommonUtils.MAX_SIZE;
import static syb.tools.CommonUtils.inputFile;

/**
 * @author Lenovo
 */
@WebServlet(name = "AddGoodsServlet", value = "/addGoodsServlet")
@MultipartConfig
public class AddGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Goods goods = new Goods();
        Long id = 0L;
        try {
            id = GoodsDao.getInstance().getAddingGoodsId();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(id);
        goods.setGoodsName(request.getParameter("goods_name"));
        goods.setGoodsInfo(request.getParameter("goods_info"));
        goods.setGoodsType(request.getParameter("goods_type"));
        goods.setInventory(Integer.parseInt(request.getParameter("inventory")));
        goods.setPrice(Double.parseDouble(request.getParameter("price")));
        goods.setSellerNum(request.getSession().getAttribute("username").toString());
        //获取商品图片
        Part part = request.getPart("goods_picture");
        String inputName = part.getSubmittedFileName();
        String suffix = inputName.substring(inputName.lastIndexOf('.')+1);
        try {
            goods.setGoodsPicture(String.format("%s%d.%s", goods.getSellerNum(), GoodsDao.getInstance().getAddingGoodsId(), suffix));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(part.getSize()>MAX_SIZE){
            String lastURL = request.getHeader("Referer");
            response.getWriter().write("<script language='javascript'>alert('传入照片过大!')</script>");
            response.getWriter().write("<script>window.location.href='"+ lastURL +"'</script>");
        } else {
            InputStream inputStream = part.getInputStream();
            //想要保存的文件目录下
            String realPath = this.getServletContext().getRealPath("/image/goods/");
            inputFile(inputStream, realPath, goods.getGoodsPicture());
            GoodsDao.getInstance().addGoods(goods);
            response.getWriter().println("新增商品成功!将在3秒后返回到我的店铺");
            response.getWriter().write("\n若无法跳转请<a href=\"myshop.jsp\">点击此处</a>");
            response.setHeader("refresh","3; myshop.jsp");

        }

    }
}
