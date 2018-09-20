package com.vishavraj.nimbl3assignmentvishavraj.data.model;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vishavraj on 16/09/18.
 */

public class LoginModel {


    /**
     * access_token : 172b02ae3eae7eb0600f564de88b8e151d4c7af80679724b08d9703d71dfa56f
     * token_type : bearer
     * expires_in : 7200
     * created_at : 1537092423
     */

    private String access_token;
    private String token_type;
    private int expires_in;
    private int created_at;

    public static LoginModel objectFromData(String str) {

        return new Gson().fromJson(str, LoginModel.class);
    }

    public static LoginModel objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), LoginModel.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }
}
