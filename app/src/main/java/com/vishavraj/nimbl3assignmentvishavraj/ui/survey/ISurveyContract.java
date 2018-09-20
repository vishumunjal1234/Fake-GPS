package com.vishavraj.nimbl3assignmentvishavraj.ui.survey;


import com.vishavraj.nimbl3assignmentvishavraj.data.model.SurveyModel;

import java.util.List;

/**
 * Created by vishavraj on 16/09/18.
 */


public interface ISurveyContract {

    public interface ISurveyPresenter {
        void getSurveyData();
        void login();

    }

    public interface ISurveyView {
       void showMessage(String message);
        void callSurveyData();
        void populateDataToAdpater(List<SurveyModel.SurveyDataBean> surveyDataBeans);

    }
}
