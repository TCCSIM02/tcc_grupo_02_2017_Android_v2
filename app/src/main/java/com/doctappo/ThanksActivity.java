package com.doctappo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import Config.ConstValue;
import models.ActiveModels;
import models.BusinessModel;

public class ThanksActivity extends CommonActivity {
    TextView txtBusinessName, txtTotalTime, txtAmount, txtDate, txtTimeSlot;
    ImageView imageView;
    BusinessModel selected_business;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        setHeaderTitle(getString(R.string.thankyou));
        selected_business = ActiveModels.BUSINESS_MODEL;

        txtAmount = (TextView) findViewById(R.id.totalAmount);
        txtTotalTime = (TextView) findViewById(R.id.totalTime);
        txtDate = (TextView) findViewById(R.id.textdate);
        txtTimeSlot = (TextView) findViewById(R.id.texttime);
        txtBusinessName = (TextView) findViewById(R.id.textBusinessName);
        imageView = (ImageView) findViewById(R.id.logoImage);

        txtAmount.setText( String.valueOf(common.get_service_total_amount() + Double.valueOf(selected_business.getBus_fee())));
        txtTotalTime.setText(common.get_service_total_times_string());
        Picasso.with(this).load(ConstValue.BASE_URL + "/uploads/business/" + ActiveModels.BUSINESS_MODEL.getBus_logo()).into(imageView);
        txtBusinessName.setText(ActiveModels.BUSINESS_MODEL.getBus_title());

        txtDate.setText(getString(R.string.date)+" : "+getIntent().getExtras().getString("date"));
        txtTimeSlot.setText(getString(R.string.time)+" : "+getIntent().getExtras().getString("timeslot"));
    }
    public void orderFinish(View view){
        ActiveModels.reset();
        Intent intent = new Intent(ThanksActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }
    public void myOrderActivity(View view){
        ActiveModels.reset();
        Intent intent = new Intent(ThanksActivity.this, MyAppointmentsActivity.class);

        startActivity(intent);
        finish();
    }

}
