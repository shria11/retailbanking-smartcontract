package com.mycompany.retailbanking.smartcontract;

/**
 *
 * @author shria
 */
public class Transaction {
    String address;
    String amount;
    String user;
    String timestamp;
    String status;
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" + "address=" + address + ", amount=" + amount + ", user=" + user + ", timestamp=" + timestamp + ", status=" + status + '}';
    }
    

      
}
