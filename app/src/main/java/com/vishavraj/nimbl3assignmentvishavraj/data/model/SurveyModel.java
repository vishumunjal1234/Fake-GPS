package com.vishavraj.nimbl3assignmentvishavraj.data.model;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by vishavraj on 16/09/18.
 */

public class SurveyModel {


    private List<SurveyDataBean> surveyData;

    public static SurveyModel objectFromData(String str) {

        return new Gson().fromJson(str, SurveyModel.class);
    }

    public static SurveyModel objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SurveyModel.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<SurveyDataBean> getSurveyData() {
        return surveyData;
    }

    public void setSurveyData(List<SurveyDataBean> surveyData) {
        this.surveyData = surveyData;
    }

    public static class SurveyDataBean {
        /**
         * id : d5de6a8f8f5f1cfe51bc
         * title : Scarlett Bangkok
         * description : We'd love ot hear from you!
         * cover_image_url : https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_
         * type : Restaurant
         */

        private String id;
        private String title;
        private String description;
        private String cover_image_url;
        private String type;

        public static SurveyDataBean objectFromData(String str) {

            return new Gson().fromJson(str, SurveyDataBean.class);
        }

        public static SurveyDataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), SurveyDataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCover_image_url() {
            return cover_image_url;
        }

        public void setCover_image_url(String cover_image_url) {
            this.cover_image_url = cover_image_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
