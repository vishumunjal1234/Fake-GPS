package com.vishavraj.nimbl3assignmentvishavraj.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishavraj.nimbl3assignmentvishavraj.dagger.ApplicationContext;
import com.vishavraj.nimbl3assignmentvishavraj.data.preferences.PreferencesHelper;
import com.vishavraj.nimbl3assignmentvishavraj.data.preferences.PreferencesManager;
import com.vishavraj.nimbl3assignmentvishavraj.utils.Nimbl3Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


/**
 * Created by vinit on 1/1/2016.
 */
public class APIManager implements IAPIManager {

    public static int VOLLEY_TIMEOUT = 30 * 1000; // 10 Sec.
    public static int VOLLEY_NUMBER_OF_ATTEMPTS = 2;
    public static float VOLLEY_BACK_OF_MULTIPLIER = 2;
    private static APIManager instance = null;
    private final Context context;
    private final PreferencesManager preferencesManager;
    private String TAG = APIManager.class.getSimpleName();
    private RequestQueue mRequestQueue;


    @Inject
    public APIManager(@ApplicationContext Context context, PreferencesManager preferencesManager) {
        this.context = context;
        this.preferencesManager = preferencesManager;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {

        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }


    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        mRequestQueue.getCache().clear();
        return mRequestQueue;
    }



    public void stringRequestWithHeaders( final String url, final VolleyJsonCallBack callback) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            callback.onSuccess(new JSONObject(response));
                        } catch (Exception e) {
                            Log.e(TAG,e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.getCause() instanceof UnknownHostException) {
                            Log.e(TAG, "UnknownHostException");
                            callback.onError(error);

                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("grant_type", "password");
                params.put("username", "carlos@nimbl3.com");
                params.put("password", "antikera");
                return params;
            }

        };
        //add to the request queue
        addToRequestQueue(stringRequest);
    }




    public void jsonObjectRequestWithHeaders(int method, final String url, final JSONObject body, final VolleyJsonCallBack callback) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, body, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    callback.onSuccess(response);
                } catch (Exception e) {
                    Log.e(TAG,e.toString());
                }


            }
        }

                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error.getCause() instanceof UnknownHostException) {
                    Log.e(TAG, "UnknownHostException");
                    callback.onError(error);

                }
            }
        }

        )

        {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put( Nimbl3Constants.ACCESS_TOKEN_KEY  , Nimbl3Constants.BEARER +preferencesManager.readString(PreferencesHelper.access_token));
                return headers;
            }
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString =
                            new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("surveyData", new JSONArray(jsonString));

                    return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }



        };
        try {
//            if (method == com.android.volley.Request.Method.PUT || method == com.android.volley.Request.Method.POST) {
//                jsonObjectRequest.setShouldCache(false);
//            }
        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        addToRequestQueue(jsonObjectRequest);
    }



    @Override
    public void request() {
        Log.e(TAG, "Linking done");
    }


    public interface VolleyJsonCallBack {
        void onSuccess(JSONObject object);

        void onError(VolleyError error);
    }


    public interface VolleyJsonArrayCallBack {
        void onSuccess(JSONArray object);

        void onError(VolleyError error);
    }
}
