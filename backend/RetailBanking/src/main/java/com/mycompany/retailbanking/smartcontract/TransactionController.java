package com.mycompany.retailbanking.smartcontract;

import com.google.gson.Gson;
import com.mycompany.retailbanking.exceptions.DuplicateEntityException;
import spark.Request;
import spark.Response;

/**
 *
 * @author shria
 */
public class TransactionController {
    /**
     *
     * @param req
     * @param res
     * @return
     */
    public static String add(Request req,Response res){
               res.type("text/plain");
        Transaction transaction = new Transaction();
                try {
              transaction = new Gson().fromJson(req.body(), Transaction.class);
                    
                    
                  
        } catch (Exception ex) {
          
            res.status(422);
            return "Invalid user model found.";
        }
                try{
                    if(!TransactionDbDao.checkAddress(transaction.getAddress())){
                        res.status(404);
                    return "Unauthorized address";}
                    
                    
        if (TransactionDbDao.add(transaction)) {
            res.status(200);
                return "New transaction recorded successfully.";
            } else {
               
                res.status(500);
                return "Failure occured while adding new transaction.";
            }

        } catch (DuplicateEntityException ex) {
            res.status(409);
            return ex.getMessage();
        } catch (Exception ex) {

            res.status(500);
            return "Error in adding transaction.";
        }
       }
    
      /**
     *
     * @param req
     * @param res
     * @return
     */
    public static String list(Request req, Response res){
        try{
        String jsonUserList = new Gson().toJson(TransactionDbDao.list());
            res.status(200);
            res.type("application/json");
            return jsonUserList;
        } catch (Exception ex) {
            res.status(500);
            return "Error in getting Transaction list.";
        }
        
   }
    
    
    
}
