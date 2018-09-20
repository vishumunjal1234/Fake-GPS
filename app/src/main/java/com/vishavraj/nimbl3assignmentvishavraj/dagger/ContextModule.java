package com.vishavraj.nimbl3assignmentvishavraj.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vishavraj on 20/09/18.
 */
@Module
public class ContextModule {

    private Context myContext;


    public ContextModule(Context context){
        this.myContext = context;
    }


    @Provides
    Context context() {
        return myContext;
    }
}
