package com.vishavraj.nimbl3assignmentvishavraj.ui.newScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vishavraj.nimbl3assignmentvishavraj.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class NewScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_screen);
        ButterKnife.bind(this);

    }

    @Optional
    @OnClick(R.id.bt_back)
    public void onBack(View view) {
        finish();

    }

}
