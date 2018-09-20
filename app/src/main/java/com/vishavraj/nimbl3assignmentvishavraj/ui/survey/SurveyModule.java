package com.vishavraj.nimbl3assignmentvishavraj.ui.survey;

import android.support.v7.app.AppCompatActivity;

import com.vishavraj.nimbl3assignmentvishavraj.dagger.scope.PerActivity;
import com.vishavraj.nimbl3assignmentvishavraj.ui.base.BaseActivityModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by vishavraj on 16/09/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class SurveyModule {

    @Binds
    @PerActivity
    abstract AppCompatActivity appCompatActivity(SurveyActivity surveyActivity);


    @PerActivity
    @Binds
    abstract ISurveyContract.ISurveyView provideNotificationHistoryView(SurveyActivity surveyActivity);


    @PerActivity
    @Binds
    abstract ISurveyContract.ISurveyPresenter provideNotificationHistoryPresenter(SurveyPresenter surveyPresenter);
}
