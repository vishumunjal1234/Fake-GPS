package com.vishavraj.nimbl3assignmentvishavraj.network;

import android.content.Context;

import com.vishavraj.nimbl3assignmentvishavraj.dagger.ApplicationContext;
import com.vishavraj.nimbl3assignmentvishavraj.data.preferences.PreferencesManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vishavraj on 16/09/18.
 */
@Module
 public class APIManagerModule {

    @Provides
    public PreferencesManager providePreferencesManager(@ApplicationContext  Context context) {
        return new PreferencesManager(context);
    }




    @Provides
    public APIManager provideApiManager(Context context, PreferencesManager preferencesManager) {
        return new APIManager(context, preferencesManager);
    }

}
