package syb.model;

/**
 * user entity.
 * @author Lenovo
 */
public class User {
    private String userID;
    private String userName;
    private String password;
    private String email;
    private String sex;
    private String address;
    private Double balance;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}
    public User(String userID, String userName, String email, String sex, String address) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.sex = sex;
        this.address = address;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
