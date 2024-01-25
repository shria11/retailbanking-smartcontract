package com.mycompany.retailbanking.user;

/**
 *
 * @author shria
 */
public class User {
    private String username;
    private String address;
    private String publicKey;
    private String privateKey;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + ", username=" + username + ", address=" + address + ", publicKey=" + publicKey + ", privateKey=" + privateKey + ", password=" + password + '}';
    }
       
}
