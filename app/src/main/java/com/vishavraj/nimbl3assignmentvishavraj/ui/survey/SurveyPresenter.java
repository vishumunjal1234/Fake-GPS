package com.vishavraj.nimbl3assignmentvishavraj.ui.survey;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishavraj.nimbl3assignmentvishavraj.dagger.ApplicationContext;
import com.vishavraj.nimbl3assignmentvishavraj.data.model.LoginModel;
import com.vishavraj.nimbl3assignmentvishavraj.data.model.SurveyModel;
import com.vishavraj.nimbl3assignmentvishavraj.data.preferences.PreferencesHelper;
import com.vishavraj.nimbl3assignmentvishavraj.data.preferences.PreferencesManager;
import com.vishavraj.nimbl3assignmentvishavraj.network.APIManager;
import com.vishavraj.nimbl3assignmentvishavraj.utils.APIUtility;
import com.vishavraj.nimbl3assignmentvishavraj.utils.CommonUtility;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by vishavraj on 16/09/18.
 */


public class SurveyPresenter implements ISurveyContract.ISurveyPresenter {

    private static final String TAG = SurveyPresenter.class.getSimpleName();

    @Inject
    @ApplicationContext
    Context context;

    @Inject
    APIManager apiManager;

    @Inject
    PreferencesManager preferencesManager;

    @Inject
    ISurveyContract.ISurveyView mView;
    private GsonBuilder gsonBuilder;
    private Gson gson;

    @Inject
    SurveyPresenter() {
        Log.i(TAG, "Initializing SurveyPresenter View");

    }
    @Override
    public void getSurveyData() {
        if (!CommonUtility.getConnectivityStatus(context)) {
            Toast.makeText(context.getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG);
            mView.showMessage("Please check your internet connection");
        } else {
            apiManager.jsonObjectRequestWithHeaders(Request.Method.GET, APIUtility.SURVEY_DATA_API, null, new APIManager.VolleyJsonCallBack() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {

                        gsonBuilder = new GsonBuilder().serializeNulls();
                        gson = gsonBuilder.create();

                        SurveyModel surveyModel = gson.fromJson(response.toString(), SurveyModel.class);
                        Log.e(TAG, "onResponse :-" + surveyModel.toString());
                            if (null != surveyModel.getSurveyData() && surveyModel.getSurveyData().size() > 0) {
                                mView.populateDataToAdpater(surveyModel.getSurveyData());
                            } else {
                            }

                    } catch (Exception e) {
                        Log.e(TAG, e.toString());
                    }
                }

                @Override
                public void onError(VolleyError error) {

                }
            });
        }
    }

    @Override
    public void login() {

        if (!CommonUtility.getConnectivityStatus(context)) {
            Toast.makeText(context.getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG);
            mView.showMessage("Please check your internet connection");
        } else {
            apiManager.stringRequestWithHeaders(APIUtility.LOGIN_API, new APIManager.VolleyJsonCallBack() {
                @Override
                public void onSuccess(JSONObject object) {
                    try {

                        gsonBuilder = new GsonBuilder().serializeNulls();
                        gson = gsonBuilder.create();

                        LoginModel loginModel = gson.fromJson(object.toString(), LoginModel.class);

                        preferencesManager.writeString(PreferencesHelper.access_token,loginModel.getAccess_token());
                        mView.callSurveyData();
                    } catch (Exception e) {
                        Log.e(TAG, e.toString());
                    }

                }

                @Override
                public void onError(VolleyError error) {

                }
            });

        }


    }
}
