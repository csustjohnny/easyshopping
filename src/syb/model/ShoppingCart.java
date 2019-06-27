package syb.model;

import java.math.BigInteger;

/**
 * shopping cart entity.
 * @author Lenovo
 */
public class ShoppingCart {

	private int cartId;
	private String	customerNum;
	private BigInteger goodsNum;
	private int number;
	private String payState;
	private String comments;
	public ShoppingCart() {

	}
	public ShoppingCart(String customerNum, BigInteger goodsNum, int number, String payState) {
		this.customerNum = customerNum;
		this.goodsNum = goodsNum;
		this.number = number;
		this.payState = payState;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public BigInteger getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(BigInteger goodsNum) {
		this.goodsNum = goodsNum;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPayState() {
		return payState;
	}
	public void setPayState(String payState) {
		this.payState = payState;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

}
