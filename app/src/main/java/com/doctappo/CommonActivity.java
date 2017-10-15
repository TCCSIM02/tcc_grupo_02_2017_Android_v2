package com.doctappo;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import util.CommonClass;

public abstract class CommonActivity extends AppCompatActivity {
    public CommonClass common;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        common = new CommonClass(this);

        super.onCreate(savedInstanceState);
    }
    public void allowBack(){
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    public Typeface getCustomFont(){
        return  Typeface.createFromAsset(getAssets(), "LobsterTwo-BoldItalic.ttf");
    }
    public void setHeaderTitle(String title){
        Typeface font = getCustomFont();

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custome_header_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.action_bar_title);
        mTitleTextView.setTypeface(font);
        mTitleTextView.setText(title);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
    public void setProgressBarAnimation(ProgressBar progressBar1){
        ObjectAnimator animation = ObjectAnimator.ofInt (progressBar1, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        animation.setDuration (5000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());
        animation.setRepeatCount(AlphaAnimation.INFINITE);
        animation.start ();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
