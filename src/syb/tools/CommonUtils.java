package syb.tools;

import syb.model.Goods;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommonUtils {
    public final static int MAX_SIZE = 1024*1024;
    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void inputFile(InputStream inputStream, String realPath, String goodsPicture) throws IOException {
        File file = new File(realPath);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        OutputStream outputStream = new FileOutputStream(new File(realPath, goodsPicture));
        int len = 0;
        byte[] buff = new byte[1024 * 8];
        while ((len = inputStream.read(buff)) > -1) {
            outputStream.write(buff, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }
    public static void setGoods(ResultSet rs, List<Goods> goodsList) throws SQLException {
        while (rs.next()){
            Goods goods = new Goods();
            goods.setGoodsNum(BigInteger.valueOf(rs.getLong("goods_num")));
            goods.setGoodsName(rs.getString("goods_name"));
            goods.setGoodsType(rs.getString("goods_type"));
            goods.setPrice(rs.getDouble("price"));
            goods.setGoodsInfo(rs.getString("goods_info"));
            goods.setGoodsPicture(rs.getString("goods_picture"));
            goods.setInventory(rs.getInt("inventory"));
            goods.setSellerNum(rs.getString("seller_num"));
            goodsList.add(goods);
        }
    }
}
