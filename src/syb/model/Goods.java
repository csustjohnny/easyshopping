package syb.model;

import java.math.BigInteger;

/**
 * the goods entity.
 * @author Lenovo
 */
public class Goods {

	private BigInteger goodsNum;
	private String goodsName;
	private String goodsType;
	private double price;
	private String goodsInfo;
	private String goodsPicture;
	private int inventory;
	private String sellerNum;
	public Goods() {}

	public Goods(BigInteger goodsNum, String goodsName, String goodsType, double price, String goodsInfo,
			String goodsPicture, int inventory, String sellerNum) {
		this.goodsNum = goodsNum;
		this.goodsName = goodsName;
		this.goodsType = goodsType;
		this.price = price;
		this.goodsInfo = goodsInfo;
		this.goodsPicture = goodsPicture;
		this.inventory = inventory;
		this.sellerNum = sellerNum;
	}
	public BigInteger getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(BigInteger goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public String getGoodsPicture() {
		return goodsPicture;
	}
	public void setGoodsPicture(String goodsPicture) {
		this.goodsPicture = goodsPicture;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getSellerNum() {
		return sellerNum;
	}
	public void setSellerNum(String sellerNum) {
		this.sellerNum = sellerNum;
	}
	
}
