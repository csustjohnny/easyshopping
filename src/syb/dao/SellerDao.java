package syb.dao;

import syb.model.Goods;
import syb.model.Seller;
import syb.tools.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static syb.tools.CommonUtils.setGoods;

/**
 * @author Lenovo
 */
public class SellerDao {
    private static  SellerDao sellerDao = null;
    public static SellerDao getInstance(){
        if(sellerDao == null){
            sellerDao = new SellerDao();
        }
        return sellerDao;
    }

    /**
     * add a new seller
     * @param seller the information of the seller
     */
    public void addSeller(Seller seller){
        Connection conn;
        PreparedStatement stmt;
        try{
            conn = JDBCUtils.getConnection();
            String sql = "insert into seller_info(seller_num,shop_name, shop_logo, shop_type, shop_description, address, email" +
                    ") values(?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,seller.getSellerNum());
            stmt.setString(2,seller.getShopName());
            stmt.setString(3,seller.getShopLogo());
            stmt.setString(4,seller.getShopType());
            stmt.setString(5,seller.getShopDescription());
            stmt.setString(6,seller.getAddress());
            stmt.setString(7,seller.getEmail());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * check whether the user is a seller.
     * @param sellerNum the userNum to be checked.
     * @return the information of the seller,if is null,it means that the user is not a seller.
     */
    public Seller checkSeller(String sellerNum){
        Connection conn;
        Statement stmt;
        ResultSet rs;
        try{
            conn = JDBCUtils.getConnection();
            String sql = "select * from seller_info where seller_num='" +
                    sellerNum + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                Seller seller = new Seller();
                seller.setSellerNum(sellerNum);
                seller.setShopLogo(rs.getString("shop_logo"));
                seller.setEmail(rs.getString("email"));
                seller.setAddress(rs.getString("address"));
                seller.setShopDescription(rs.getString("shop_description"));
                seller.setShopType(rs.getString("shop_type"));
                return seller;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * update the information of a seller
     * @param seller the information to be updated.
     */
    public void updateSeller(Seller seller) throws SQLException, ClassNotFoundException {
        String sellerNum = seller.getSellerNum();
        String shopName = seller.getShopName();
        String shopLogo = seller.getShopLogo();
        String shopDescription = seller.getShopDescription();
        String shopType = seller.getShopType();
        String shopAddress = seller.getAddress();
        String email = seller.getEmail();
        Connection connection = JDBCUtils.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "update seller_info " + "set shop_name='" + shopName + "'," +
                " shop_logo='" + shopLogo + "'," +
                "shop_description='" + shopDescription + "'," +
                "shop_type='" + shopType + "'," +
                "address='" + shopAddress + "'," +
                "email='" + email + "'" +
                "where seller_num = '" + sellerNum + "'";
        stmt.executeUpdate(sql);

    }

    /**
     * get goods under the sellerNum.
     * @param sellerNum the sellerNum of the seller.
     * @return a List of the goods.
     */
    public List<Goods> getSellerGoods(String sellerNum) throws SQLException, ClassNotFoundException {
        Connection conn = JDBCUtils.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "select * from goods_info where seller_num = '" + sellerNum + "'";
        ResultSet rs = stmt.executeQuery(sql);
        List<Goods> goodsList = new ArrayList<>();
        setGoods(rs, goodsList);
        return goodsList;
    }

    /**
     * delete goods under the seller.
     * @param goodsNum goods_number
     * @param seller_num seller_num
     */
    public void removeGoods(String goodsNum, String seller_num) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "delete from goods_info where goods_num='" + goodsNum +
                "',and seller_num='" + seller_num + "'";
        statement.executeUpdate(sql);
    }

}
