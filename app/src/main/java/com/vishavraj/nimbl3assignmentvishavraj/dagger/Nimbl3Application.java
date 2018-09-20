package com.vishavraj.nimbl3assignmentvishavraj.dagger;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by vishavraj on 20/09/18.
 */

public class Nimbl3Application extends MultiDexApplication implements HasActivityInjector {

    private static final String TAG = Nimbl3Application.class.getName();

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerIApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }




}
