package syb.dao;

import syb.model.ShoppingCart;
import syb.tools.JDBCUtils;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private static CartDao cartDao;
    public static CartDao getInstance(){
        if(cartDao==null){
            cartDao = new CartDao();
        }
        return cartDao;
    }

    /**
     * add the goods to the shopping cart.
     * @param cart the information of the shopping cart.
     * @return true or false.
     */
    public boolean addShoppingCart(ShoppingCart cart) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "insert into shopping_cart(customer_num,goods_num,number,pay_state,comments) values('" +
                cart.getCustomerNum() + "', '" + cart.getGoodsNum() + "'," + cart.getNumber() + ",'" +
                cart.getPayState() + "','" + cart.getComments() + "')";
        System.out.println(sql);
        return statement.executeUpdate(sql) > 0;
    }

    public List<String> getCommentsByGoodsId(String goodsId) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        List<String> commentsList = new ArrayList<>();
        String sql = "select comments from shopping_cart where goods_num=" + goodsId;
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            commentsList.add(resultSet.getString("comments"));
        }
        return commentsList;
    }

    public List<ShoppingCart> getCartList(String customerNum){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<ShoppingCart> list = new ArrayList<>();
        try{
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();
            String sql = "select * from shopping_cart where customer_num = '" +customerNum + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ShoppingCart cart = new ShoppingCart();
                cart.setCartId(resultSet.getInt("cart_id"));
                cart.setCustomerNum(resultSet.getString("customer_num"));
                cart.setGoodsNum(BigInteger.valueOf(resultSet.getLong("goods_num")));
                cart.setNumber(resultSet.getInt("number"));
                cart.setPayState(resultSet.getString("pay_state"));
                cart.setComments(resultSet.getString("comments"));
                list.add(cart);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(resultSet,statement,connection);
        }
        return null;
    }

    public void updateComments(int id, String comments){
        Connection connection = null;
        Statement statement = null;
        try{
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();
            String sql = "update shopping_cart set comments = '" + comments + "' where cart_id = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(statement,connection);
        }
    }
}
