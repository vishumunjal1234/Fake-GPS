package com.vishavraj.nimbl3assignmentvishavraj.dagger;

/**
 * Created by vishavraj on 20/09/18.
 */

import com.vishavraj.nimbl3assignmentvishavraj.dagger.scope.PerActivity;
import com.vishavraj.nimbl3assignmentvishavraj.ui.survey.SurveyActivity;
import com.vishavraj.nimbl3assignmentvishavraj.ui.survey.SurveyModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//@Module(subcomponents = IDashboardSubcomponent.class)
@Module
abstract class ActivityBuilderModule {

    //Used @ContributesAndroidInjector as no subcomponents were present for this Activity
    @PerActivity
    @ContributesAndroidInjector(modules = SurveyModule.class)
    abstract SurveyActivity surveyActivityInjector();


}