package syb.model;

/**
 * the seller entity.
 * @author Lenovo
 */
public class Seller {
	private String sellerNum;
	private String shopName;
	private String shopLogo;
	private String shopType;
	private String shopDescription;
	private String address;
	private String email;
	public Seller(){}
	public Seller(String sellerNum, String shopName, String shopLogo, String shopType, String shopDescription,
			String address, String email) {
		this.sellerNum = sellerNum;
		this.shopName = shopName;
		this.shopLogo = shopLogo;
		this.shopType = shopType;
		this.shopDescription = shopDescription;
		this.address = address;
		this.email = email;
	}
	public String getSellerNum() {
		return sellerNum;
	}
	public void setSellerNum(String sellerNum) {
		this.sellerNum = sellerNum;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	public String getShopDescription() {
		return shopDescription;
	}
	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
