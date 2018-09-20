package com.vishavraj.nimbl3assignmentvishavraj.ui.base;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.vishavraj.nimbl3assignmentvishavraj.dagger.ActivityContext;
import com.vishavraj.nimbl3assignmentvishavraj.dagger.scope.PerActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vishavraj on 16/09/18.
 */

@Module
public abstract class BaseActivityModule {

    static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";

    @Binds
    @PerActivity
    @ActivityContext
    abstract Context activityContext(AppCompatActivity activity);


    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    @PerActivity
    static FragmentManager activityFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
