package syb.dao;

import jdk.nashorn.internal.scripts.JD;
import syb.model.Goods;
import syb.model.ShoppingCart;
import syb.model.User;
import syb.tools.JDBCUtils;

import javax.xml.transform.Result;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Lenovo
 */
public class UserDAO {

    /**
     * add user
     *
     * @param user the information that the user to be added.
     * @return true or false
     */
    public static boolean addUser(User user) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "insert into user(userId,password,userName,email,sex,address)" +
                    "values('" + user.getUserID() + "','" + user.getPassword() + "','" +
                    user.getUserName() + "','" + user.getEmail() + "','" + user.getSex() + "','" +
                    user.getAddress() + "')";
            int num = stmt.executeUpdate(sql);
            return num > 0;
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            JDBCUtils.release(stmt, conn);
        }
        return false;
    }

    /**
     * check whether the name is in the database,
     * if it's in it,the method will return a User class.
     *
     * @param name username
     * @return a User class.
     */
    public static User checkUser(String name) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from user where userID = '" + name + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getString("sex"));
                user.setAddress(rs.getString("address"));
                user.setBalance(rs.getDouble("balance"));
                return user;
            } else {
                return null;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return null;
    }

    /**
     * check whether the password and the name is right.
     *
     * @param name     username
     * @param password password
     * @return true of false.
     */
    public static boolean checkPassword(String name, String password) {
        User user = UserDAO.checkUser(name);
        return user != null && password.equals(user.getPassword());
    }

    public static double getUserBalanceById(String userNumber) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();
            String sql = "select balance from user where userId = '" + userNumber + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(resultSet,statement,connection);
        }
        return 0;
    }

    /**
     * add goods to shopping cart.
     *
     * @param goodsNum    goods id.
     * @param number      the number buying.
     * @param buyerNumber the customer's id.
     * @return the status.
     */
    public static String buyGoods(String goodsNum, int number, String buyerNumber) throws SQLException, ClassNotFoundException {
        Goods goods = GoodsDao.getInstance().getGoodsById(goodsNum);
        User user = UserDAO.checkUser(buyerNumber);
        User sellerUser = UserDAO.checkUser(goods.getSellerNum());
        int inventory = goods.getInventory();
        double totalPrice = goods.getPrice() * number;
        if (user == null) {
            return "请先登录";
        }
        if (number > inventory) {
            return "库存不够";
        } else if (totalPrice > getUserBalanceById(buyerNumber)) {
            return "余额不足";
        } else {
            //修改商品库存
            goods.setInventory(goods.getInventory() - number);
            GoodsDao.getInstance().updateGoods(goods);
            //修改用户余额
            user.setBalance(user.getBalance() - totalPrice);
            updateUser(user);
            //修改商家用户余额
            sellerUser.setBalance(sellerUser.getBalance() + totalPrice);
            updateUser(sellerUser);
            ShoppingCart cart = new ShoppingCart();
            cart.setPayState("支付成功");
            cart.setCustomerNum(buyerNumber);
            cart.setNumber(number);
            cart.setGoodsNum(BigInteger.valueOf(Long.parseLong(goodsNum)));
            cart.setComments("");
            CartDao.getInstance().addShoppingCart(cart);
            return "购买成功";
        }
    }

    public static void updateUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();

        String sql = "update user set username='" + user.getUserName()
                + "',email='" + user.getEmail() + "', sex='" + user.getSex() + "',address='" + user.getAddress()
                + "',balance=" + user.getBalance() + " where userId = '" + user.getUserID() + "'";
        System.out.println("updateSql: " + sql);
        statement.executeUpdate(sql);
        JDBCUtils.release(statement, connection);
    }
}
