package syb.servlet;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * @author Lenovo
 */
@WebServlet(name = "TestServlet", value = "/TestServlet")
@MultipartConfig
public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        //创建文件上传处理器
        //ServletFileUpload upload = new ServletFileUpload();
        /*request.setCharacterEncoding("UTF-8");
        Part part = request.getPart("img");
        String inputName = part.getSubmittedFileName();
        String suffix = inputName.substring(inputName.lastIndexOf('.')+1);

        System.out.println(part.getSize());
        InputStream inputStream = part.getInputStream();
        //想要保存的文件目录下
        String realPath = this.getServletContext().getRealPath("/image/user/");
        File file = new File(realPath);
        if(!file.isDirectory()){
            file.mkdirs();
        }
        OutputStream outputStream = new FileOutputStream(new File(realPath,inputName));
        int len = 0;
        byte[] buff = new byte[1024*8];
        while((len = inputStream.read(buff))>-1){
            outputStream.write(buff, 0, len);
        }
        inputStream.close();
        outputStream.close();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("upload success!");*/
        //URL path = this.getClass().getClassLoader().getResource("");
        //System.out.println(realPath);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
