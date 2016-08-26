package com.oft.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.oft.aspect.logger.Loggable;
import com.oft.pojo.User;

@Path("/register")
public class Register {
    // HTTP Get Method
    @POST
    // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/doregister")  
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public String doLogin( User form){
        String response = "";
        //System.out.println("Inside doLogin "+uname+"  "+pwd);
      
       
        return response;
 
    }
 
 
    
  
}
