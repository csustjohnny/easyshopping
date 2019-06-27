package syb.dao;



import syb.model.Goods;
import syb.model.ShoppingCart;
import syb.tools.JDBCUtils;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static syb.tools.CommonUtils.setGoods;


/**
 * @author Lenovo
 */
public class GoodsDao {
	private static GoodsDao goodsDao = null;
    public static GoodsDao getInstance(){
        if(goodsDao==null){
            goodsDao = new GoodsDao();
        }
        return goodsDao;
    }
	/**
	 * get all the information of all goods.
	 * @return a list of information.
	 */
	public  List<Goods> getAllGoods(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from goods_info";
			rs = stmt.executeQuery(sql);
			setGoods(rs, list);
			return list;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}

    /**
     * add a new goods.
     * @param goods the information of the goods.
     */
	public void addGoods(Goods goods){
		Connection conn;
		PreparedStatement stmt;
		try{
			conn = JDBCUtils.getConnection();
			String sql = "insert into goods_info(goods_name,goods_type,price,goods_info,goods_picture,inventory,seller_num) values" +
					"(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,goods.getGoodsName());
			stmt.setString(2,goods.getGoodsType());
			stmt.setDouble(3,goods.getPrice());
			stmt.setString(4,goods.getGoodsInfo());
			stmt.setString(5,goods.getGoodsPicture());
			stmt.setInt(6,goods.getInventory());
			stmt.setString(7,goods.getSellerNum());
			stmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    /**
     * get the id that the goods to be added.
     * @return Long
     */
	public Long getAddingGoodsId() throws SQLException, ClassNotFoundException {
		Connection conn = JDBCUtils.getConnection();
		Statement statement = conn.createStatement();
		String sql = "select goods_num from goods_info order by goods_num desc limit 0,1";
		ResultSet resultSet = statement.executeQuery(sql);

		if(resultSet.next()){
			return resultSet.getLong("goods_num") + 1L;
		}else {
			return 0L;
		}
	}

	/**
	 * update the information of the goods.
	 * @param goods the new information of the goods.
	 * @return true or false.
	 */
	public boolean updateGoods(Goods goods) throws SQLException, ClassNotFoundException {
		Connection connection = JDBCUtils.getConnection();
		Statement statement = connection.createStatement();
		String sql = "update goods_info set goods_name='" + goods.getGoodsName() + "',"
				+ " goods_type='" + goods.getGoodsType() + "', price=" + goods.getPrice()
				+ ", goods_info='" + goods.getGoodsInfo() + "', inventory=" + goods.getInventory()
				+ " where goods_num=" + goods.getGoodsNum();
		System.out.println("updateGoods: "+sql);
		return statement.executeUpdate(sql) > 0;
	}

	/**
	 * delete goods.
	 * @param goodsNum the id of the goods.
	 * @param sellerNum the id of the seller.
	 * @return true means success and false means fail.
	 */
	public boolean deleteGoods(String goodsNum, String sellerNum) throws SQLException, ClassNotFoundException {
		Connection connection = JDBCUtils.getConnection();
		Statement statement = connection.createStatement();
		String sql = "delete from goods_info where goods_num='" + goodsNum + "'" +
				"and seller_num='" + sellerNum + "'";
		return statement.executeUpdate(sql) > 0;
	}


	/**
	 *  get the number of the goods for sale.
	 * @param goodsNum the goods id.
	 * @param statement Statement for connection.
	 * @return the number of the goods.
	 */
	private int getGoodsForSale(String goodsNum, Statement statement) throws SQLException {
		String sql = "select inventory from goods_info where goods_num='" + goodsNum + "'";
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next()){
			return resultSet.getInt("inventory");
		}
		return -1;
	}

    /**
     * get goods by Id.
     * @param goods_num goods id.
     * @return goods object.
     */
	public Goods getGoodsById(String goods_num) throws SQLException, ClassNotFoundException {
		Connection connection = JDBCUtils.getConnection();
		Statement statement = connection.createStatement();
		String sql = "select * from goods_info where goods_num='" + goods_num + "'";
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next()){
			Goods goods  = new Goods();
			goods.setGoodsNum(BigInteger.valueOf(resultSet.getLong("goods_num")));
			goods.setSellerNum(resultSet.getString("seller_num"));
			goods.setPrice(resultSet.getDouble("price"));
			goods.setInventory(resultSet.getInt("inventory"));
			goods.setGoodsInfo(resultSet.getString("goods_info"));
			goods.setGoodsType(resultSet.getString("goods_type"));
			goods.setGoodsName(resultSet.getString("goods_name"));
			goods.setGoodsPicture(resultSet.getString("goods_picture"));
			return goods;
		}
		return null;
	}


}
