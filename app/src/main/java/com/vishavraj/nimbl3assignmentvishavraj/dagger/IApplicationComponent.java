package com.vishavraj.nimbl3assignmentvishavraj.dagger;

import android.app.Application;

import com.vishavraj.nimbl3assignmentvishavraj.data.preferences.PreferencesManager;
import com.vishavraj.nimbl3assignmentvishavraj.network.APIManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by vishavraj on 20/09/18.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class,
        ActivityBuilderModule.class})

interface IApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        IApplicationComponent build();
    }

    void inject(Nimbl3Application application);


    PreferencesManager getPreferencesManager();

    APIManager getAPIManager();


    Nimbl3Application getSurveyApp();
}
