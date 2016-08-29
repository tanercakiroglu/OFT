package com.oft.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;

public class Util {

	   /**
     * Null check Method
     * 
     * @param txt
     * @return
     */
    public static boolean isNotNullOREmpty(String txt) {
        // System.out.println("Inside isNotNull");
        return txt != null && txt.trim().length() > 0 ? true : false;
    }
 
    /**
     * Method to construct JSON
     * 
     * @param tag
     * @param status
     * @return
     */
    public static JSONObject constructJSON(String tag, boolean status,Object response) {
        JSONObject obj = new JSONObject();
        Gson gson = new Gson();
        try {
        	String json = gson.toJson(response);
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
            obj.put("response", json);
        } catch (JSONException e) {
        	obj=null;
        }
        return obj;
    }
 
    /**
     * Method to construct JSON with Error Msg
     * 
     * @param tag
     * @param status
     * @param err_msg
     * @return
     */
    public static JSONObject constructJSON(String tag,Object err_msg,boolean status) {
        JSONObject obj = new JSONObject();
        Gson gson = new Gson();
        try {
        	String json = gson.toJson(err_msg);
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
            obj.put("error_msg", json);
        } catch (JSONException e) {
           obj=null;
        }
        return obj;
    }
}
