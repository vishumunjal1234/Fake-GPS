package com.vishavraj.nimbl3assignmentvishavraj.data.preferences;

import android.content.Context;

import com.vishavraj.nimbl3assignmentvishavraj.dagger.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vishavraj on 16/09/18.
 */

@Module
public class PreferencesManagerModule {


    @Provides
    public PreferencesManager providePreferencesManager(@ApplicationContext Context context) {
        return new PreferencesManager(context);
    }
}
