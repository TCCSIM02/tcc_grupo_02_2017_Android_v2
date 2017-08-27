package com.doctappo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Config.ApiParams;
import models.ActiveModels;
import models.BusinessModel;
import util.NameValuePair;
import util.VJsonRequest;

public class LoginActivity extends CommonActivity {
    EditText editEmail, editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setHeaderTitle(getString(R.string.login_now));
        editEmail = (EditText)findViewById(R.id.txtEmail);
        editPassword = (EditText)findViewById(R.id.txtPassword);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            common.setToastMessage(getString(R.string.valid_required_email));
            focusView = editEmail;
            cancel = true;
        }
        if (!isValidEmail(email)) {
            common.setToastMessage(getString(R.string.valid_email));
            focusView = editEmail;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            common.setToastMessage(getString(R.string.valid_required_password));
            focusView = editPassword;
            cancel = true;
        }

        if (cancel) {
            if (focusView!=null)
                focusView.requestFocus();
        } else {
            HashMap<String,String> params= new HashMap<>();


            params.put("user_email",email);
            params.put("user_password",password);

            VJsonRequest vJsonRequest = new VJsonRequest(this, ApiParams.LOGIN_URL,params,
                    new VJsonRequest.VJsonResponce(){
                        @Override
                        public void VResponce(String responce) {

                            JSONObject userdata = null;
                            try {
                                userdata = new JSONObject(responce);

                                common.setSession(ApiParams.COMMON_KEY,userdata.getString("user_id"));
                                common.setSession(ApiParams.USER_FULLNAME,userdata.getString("user_fullname"));
                                common.setSession(ApiParams.USER_EMAIL,userdata.getString("user_email"));
                                common.setSession(ApiParams.USER_PHONE,userdata.getString("user_phone"));

                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        @Override
                        public void VError(String responce) {
                            common.setToastMessage(responce);
                        }
                    });
        }

    }
    public void forgotPassword(View view){
        String email = editEmail.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            common.setToastMessage(getString(R.string.valid_required_email));
            focusView = editEmail;
            cancel = true;
        }
        if (!isValidEmail(email)) {
            common.setToastMessage(getString(R.string.valid_email));
            focusView = editEmail;
            cancel = true;
        }
        if (cancel) {
            if (focusView!=null)
                focusView.requestFocus();
        } else {
            HashMap<String,String> params = new HashMap<>();


            params.put("user_email",email);

            VJsonRequest vJsonRequest = new VJsonRequest(this, ApiParams.FORGOT_PASSWORD_URL,params,
                    new VJsonRequest.VJsonResponce(){
                        @Override
                        public void VResponce(String responce) {
                            common.setToastMessage(responce);
                        }
                        @Override
                        public void VError(String responce) {
                            common.setToastMessage(responce);
                        }
                    });

        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public void registerClick(View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}
