package com.mycompany.retailbanking.smartcontract;

import com.mycompany.retailbanking.app.DbConnection;
import com.mycompany.retailbanking.exceptions.DuplicateEntityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shria
 */
public class TransactionDbDao {
    /**
     *
     * @param user
     * @return
     * @throws DuplicateEntityException
     * @throws Exception
     */
    protected static boolean add(Transaction transaction) throws  Exception{
         String sqlAdd = "INSERT INTO transaction(amount, status, user_username) "
                + "VALUES "
                + "(?, ?,  ?);";

        try (Connection conn = DbConnection.getCon();
                PreparedStatement stmt1 = conn.prepareStatement(sqlAdd);) { 
            stmt1.setString(1, transaction.getAmount());
            stmt1.setString(2, "authorized");
             stmt1.setString(3, transaction.getUser());
               
                  int affectedRows =   stmt1.executeUpdate();
              
                                            if (affectedRows > 0) {return true;}return false;
            
        }catch(SQLException e){
                 throw new Exception("Error in adding transaction: " + e.getMessage());
                }
     
        
       }
    
     /**
     *
     * @return
     * @throws SQLException
     * @throws Exception
     */
    protected static List<Transaction> list() throws SQLException, Exception{
         String sqlList = "select * from transaction";
             try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sqlList);) {   
                 try (ResultSet rsList = psList.executeQuery();) {
                List<Transaction> transactionList = new ArrayList<>();
                    while (rsList.next()) {
                        Transaction transaction = new Transaction();
                        transaction.setUser(rsList.getString("user_username"));
                        transaction.setAmount(rsList.getString("amount"));
transaction.setStatus(rsList.getString("status"));
transaction.setTimestamp(rsList.getString("timestamp"));
                        transactionList.add(transaction);
                    }
                    return transactionList;
                 }}catch(SQLException ex){
            throw new Exception(ex.getMessage());}
         
   }
    
         /**
     *
     * @return
     * @throws SQLException
     * @throws Exception
     */
    protected static boolean checkAddress(String address) throws SQLException, Exception{
         String sqlList = "select * from authorized_address where address='"+address+"'";
             try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sqlList);) {   
                 try (ResultSet rsList = psList.executeQuery();) {
                Transaction transaction = new Transaction();
                   if (rsList.next()) {
                        transaction.setAddress("address");
                        return true;
                    }
                    return false;
                 }}catch(SQLException ex){
            throw new Exception(ex.getMessage());}
         
   }  
}
