package com.mycompany.retailbanking.user;

import com.google.gson.Gson;
import com.mycompany.retailbanking.exceptions.DuplicateEntityException;
import com.mycompany.retailbanking.exceptions.NotFoundException;
import spark.Request;
import spark.Response;

/**
 *
 * @author shria
 */
public class UserController {
    
    /**
     *
     * @param req
     * @param res
     * @return
     */
    public static String list(Request req, Response res){
        try{
        String jsonUserList = new Gson().toJson(UserDbDao.list());
            res.status(200);
            res.type("application/json");
            return jsonUserList;
        } catch (Exception ex) {
            res.status(500);
            return "Error in getting User list.";
        }
        
   }
    
    /**
     *
     * @param req
     * @param res
     * @return
     */
    public static String add(Request req,Response res){
               res.type("text/plain");
        User user = new User();
                try {
              user = new Gson().fromJson(req.body(), User.class);                    
                    user.setAddress(getAlphaNumericString(40));
        } catch (Exception ex) {
          
            res.status(422);
            return "Invalid user model found.";
        }
                try{
        if (UserDbDao.add(user)) {
                return "New user '" + user.getUsername()+ "' added successfully.";
            } else {
               
                res.status(500);
                return "Failure occured while adding new user.";
            }

        } catch (DuplicateEntityException ex) {
            res.status(409);
            return ex.getMessage();
        } catch (Exception ex) {

            res.status(500);
            return "Error in adding user.";
        }
       }
    
    /**
     *
     * @param req
     * @param res
     * @return
     */
    public static String view(Request req,Response res){
        res.type("text/plain");
        
                String userId = req.params("id");
        try {
            int userId1 = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            res.status(422);
            return "Invalid format for user id: " + userId;
        }
        
        try {
             User user = UserDbDao.view(Integer.parseInt(userId));
            String jsonUser = new Gson().toJson(user);
            res.type("application/json");
            res.status(200);
            return jsonUser;
        } catch (NotFoundException ex) {
           
            res.status(404);
            return "User doesn't exist in the system.";
        } catch (Exception ex) {
            res.status(500);
            return "Error in viewing user.";
        }
       }
    
    /**
     *
     * @param req
     * @param res
     * @return
     */
    public static String login(Request req,Response res){
           res.type("text/plain");
        String username = req.queryParams("username");
        String pwd = req.queryParams("password");
        
        try{
        if(UserDbDao.authenticate(username, pwd)){
            res.status(200);
                   return "User login successful";
        }else{ 
            res.status(401);
            return "Username or Password doesn't match for user:" + username;}
        }catch(Exception ex){
         res.status(500);
            return "Error occured while login.";}
    }
    
    static String getAlphaNumericString(int n) 
 { 
 
  // choose a Character random from this String 
  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
         + "0123456789"
         + "abcdefghijklmnopqrstuvxyz"; 
 
  // create StringBuffer size of AlphaNumericString 
  StringBuilder sb = new StringBuilder(n); 
 
  for (int i = 0; i < n; i++) { 
 
   // generate a random number between 
   // 0 to AlphaNumericString variable length 
   int index 
    = (int)(AlphaNumericString.length() 
      * Math.random()); 
 
   // add Character one by one in end of sb 
   sb.append(AlphaNumericString 
      .charAt(index)); 
  } 
 
  return sb.toString(); 
 } 
       
}
