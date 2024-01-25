package com.mycompany.retailbanking.user;

import com.mycompany.retailbanking.app.DbConnection;
import com.mycompany.retailbanking.exceptions.DuplicateEntityException;
import com.mycompany.retailbanking.exceptions.NotFoundException;
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
public class UserDbDao {
    
    /**
     *
     * @return
     * @throws SQLException
     * @throws Exception
     */
    protected static List<User> list() throws SQLException, Exception{
         String sqlList = "select * from user";
             try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sqlList);) {   
                 try (ResultSet rsList = psList.executeQuery();) {
                List<User> userList = new ArrayList<>();
                    while (rsList.next()) {
                        User user = new User();
                        user.setUsername(rsList.getString("username"));
                        user.setPrivateKey(rsList.getString("privateKey"));
                        user.setPublicKey(rsList.getString("publicKey"));
                        user.setAddress(rsList.getString("address"));
                        userList.add(user);
                    }
                    return userList;
                 }}catch(SQLException ex){
            throw new Exception(ex.getMessage());}
         
   }
    
    /**
     *
     * @param user
     * @return
     * @throws DuplicateEntityException
     * @throws Exception
     */
    protected static boolean add(User user) throws DuplicateEntityException, Exception{
         String sqlAdd = "INSERT INTO user(username, address, publicKey, privateKey, password) "
                + "VALUES "
                + "(?, ?,  ?, ?, SHA2(?,256));";
         
             String sqlAdd1 = "INSERT INTO authorized_address(address) "
                + "VALUES ('"
                + "0x"+ user.getAddress()+"')";

        try (Connection conn = DbConnection.getCon();
                PreparedStatement stmt1 = conn.prepareStatement(sqlAdd);
                   PreparedStatement stmt2 = conn.prepareStatement(sqlAdd1);) { 
            conn.setAutoCommit(false);
            stmt1.setString(1, user.getUsername());
            stmt1.setString(2, "0x"+ user.getAddress());
             stmt1.setString(3, user.getPublicKey());
              stmt1.setString(4, user.getPrivateKey());
               stmt1.setString(5, user.getPassword());
              
               
                  int affectedRows =   stmt1.executeUpdate();
                   int affectedRows1 =   stmt2.executeUpdate();
              
                                            if (affectedRows > 0 && affectedRows1>0) {
                                                conn.commit();
                                                return true;}conn.rollback();return false;
            
        }catch(SQLException e){
            
                if (e.getErrorCode() == 1062) {
                throw new DuplicateEntityException( "Username already exists");
            }
                 throw new Exception("Error in adding user: " + e.getMessage());
                }
     
        
       }

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     * @throws Exception
     */
    protected static User view(int id) throws NotFoundException,Exception{
     String sqlView = "SELECT * from user where id="+id;
     
      try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sqlView);) {
            try (ResultSet rsList = psList.executeQuery();) {
                User  user = new User();
                if (rsList.next()) {
                        user.setUsername(rsList.getString("username"));
                        user.setPrivateKey(rsList.getString("private_key"));
                        user.setPublicKey(rsList.getString("public_key"));
                        user.setAddress(rsList.getString("address"));
                        
                        return user;
                }else{
                    throw new NotFoundException( "User not found. Invalid user id provided.");}
            }}catch(Exception ex){throw new Exception(ex.getMessage());}}    
    
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    protected static boolean authenticate(String username, String password) throws Exception{
        
     String sqlView = "SELECT * from user where username='"+username+"' and password=SHA2('"+password+"',256)";
     
      try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sqlView);) {
            try (ResultSet rsList = psList.executeQuery();) {
                User  user = new User();
                if (rsList.next()) {
                        
                        return true;
                }else{
                   return false;}
            }}catch(Exception ex){throw new Exception(ex.getMessage());}
    }
    
}
