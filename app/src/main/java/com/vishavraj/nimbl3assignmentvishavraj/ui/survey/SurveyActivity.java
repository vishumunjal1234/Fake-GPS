package com.vishavraj.nimbl3assignmentvishavraj.ui.survey;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vishavraj.nimbl3assignmentvishavraj.R;
import com.vishavraj.nimbl3assignmentvishavraj.data.model.SurveyModel;
import com.vishavraj.nimbl3assignmentvishavraj.ui.base.BaseActivity;
import com.vishavraj.nimbl3assignmentvishavraj.ui.newScreen.NewScreenActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class SurveyActivity extends BaseActivity implements ISurveyContract.ISurveyView {


    @Inject
    ISurveyContract.ISurveyPresenter mSurveyPresenter;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rl_navigation)
    LinearLayout rl_navigation;
    private ImageView[] ivArrayDotsPager;
    List<SurveyModel.SurveyDataBean> surveyDataBeans;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        dialog = new ProgressDialog(SurveyActivity.this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Loading Data");

        dialog.show();
        mSurveyPresenter.login();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.nonselecteditem_dot);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.selecteditem_dot);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupPagerIndidcatorDots() {
        ivArrayDotsPager = new ImageView[surveyDataBeans.size()];
        for (int i = 0; i < ivArrayDotsPager.length; i++) {
            ivArrayDotsPager[i] = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 100, 0, 0);
            ivArrayDotsPager[i].setLayoutParams(params);
            if (i == 0) {
                ivArrayDotsPager[i].setImageResource(R.drawable.selecteditem_dot);

            } else {
                ivArrayDotsPager[i].setImageResource(R.drawable.nonselecteditem_dot);
            }
            //ivArrayDotsPager[i].setAlpha(0.4f);
            rl_navigation.addView(ivArrayDotsPager[i], params);
            rl_navigation.bringToFront();
        }

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void callSurveyData() {
        mSurveyPresenter.getSurveyData();
    }


    @Override
    public void populateDataToAdpater(final List<SurveyModel.SurveyDataBean> surveyDataBeans) {
        this.surveyDataBeans = surveyDataBeans;
        rl_navigation.removeAllViews();
        final LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return surveyDataBeans.size();


            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                final View itemView = mLayoutInflater.inflate(R.layout.survey_card, container, false);
                TextView tv_name = itemView.findViewById(R.id.tv_name);
                TextView tv_desc = itemView.findViewById(R.id.tv_desc);
                ImageView iv_cover_image = itemView.findViewById(R.id.iv_cover_image);
                SurveyModel.SurveyDataBean surveyDataBean = surveyDataBeans.get(position);
                tv_name.setText(surveyDataBean.getTitle());
                tv_desc.setText(surveyDataBean.getDescription());
                Glide.with(getApplicationContext())
                        .load(surveyDataBean.getCover_image_url())
                        .into(iv_cover_image);

                container.addView(itemView);
                return itemView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((RelativeLayout) object);
            }
        });
        setupPagerIndidcatorDots();
        dialog.dismiss();

    }


    @Optional
    @OnClick(R.id.bt_take_survey)
    public void takeSurveyClick(View view) {
        startActivity(new Intent(getApplicationContext(), NewScreenActivity.class));

    }

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setPadding(0, 0, 0, 0);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.setContentInsetStartWithNavigation(0);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ImageButton imageButton = (ImageButton) findViewById(R.id.ib_refresh);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setTitle("Updating data");
                dialog.show();

                mSurveyPresenter.getSurveyData();

            }
        });


    }


}
