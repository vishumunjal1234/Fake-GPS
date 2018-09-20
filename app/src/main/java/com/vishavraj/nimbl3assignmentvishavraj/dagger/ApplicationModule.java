package com.vishavraj.nimbl3assignmentvishavraj.dagger;

import android.app.Application;
import android.content.Context;

import com.vishavraj.nimbl3assignmentvishavraj.data.preferences.PreferencesManager;
import com.vishavraj.nimbl3assignmentvishavraj.network.APIManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



/**
 * Created by vishavraj on 20/09/18.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public Nimbl3Application provideApplication(Application application) {
        return (Nimbl3Application) application;
    }


    @Provides
    @Singleton
    static PreferencesManager providePreferencesManager(@ApplicationContext Context context) {
        return new PreferencesManager(context);
    }





    @Provides
    @Singleton
    static APIManager provideApiManager(@ApplicationContext Context context, PreferencesManager preferencesManager) {
        return new APIManager(context, preferencesManager);
    }
}
