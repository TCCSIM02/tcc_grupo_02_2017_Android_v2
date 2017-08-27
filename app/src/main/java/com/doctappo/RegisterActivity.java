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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import Config.ApiParams;
import util.NameValuePair;
import util.VJsonRequest;

public class RegisterActivity extends CommonActivity {
    EditText editEmail, editPassword, editPhone, editFullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        allowBack();
        setHeaderTitle(getString(R.string.register_new));

        editEmail = (EditText)findViewById(R.id.txtEmail);
        editPassword = (EditText)findViewById(R.id.txtPassword);
        editFullname = (EditText)findViewById(R.id.txtFirstname);
        editPhone = (EditText)findViewById(R.id.txtPhone);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void register(){

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String fullname = editFullname.getText().toString();
        String phone = editPhone.getText().toString();
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
        if (TextUtils.isEmpty(fullname)) {
            common.setToastMessage(getString(R.string.valid_required_fullname));
            focusView = editFullname;
            cancel = true;
        }
        if (TextUtils.isEmpty(phone)) {
            common.setToastMessage(getString(R.string.valid_required_password));
            focusView = editPhone;
            cancel = true;
        }
        if (cancel) {
            if (focusView!=null)
                focusView.requestFocus();
        } else {
            HashMap<String,String> params = new HashMap<>();
            params.put("user_fullname",fullname);
            params.put("user_phone",phone);
            params.put("user_email",email);
            params.put("user_password",password);

            VJsonRequest vJsonRequest = new VJsonRequest(this, ApiParams.REGISTER_URL,params,
                    new VJsonRequest.VJsonResponce(){
                        @Override
                        public void VResponce(String responce) {
                            JSONObject userdata = null;
                            try {
                                userdata = new JSONObject(responce);
                                Intent intent = null;
                                common.setSession(ApiParams.COMMON_KEY, userdata.getString("user_id"));
                                common.setSession(ApiParams.USER_FULLNAME, userdata.getString("user_fullname"));
                                common.setSession(ApiParams.USER_EMAIL, userdata.getString("user_email"));
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
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    private final Handler handler = new Handler() {
        public void handleMessage(Message message) {
            if (message.getData().containsKey(ApiParams.PARM_RESPONCE)){
                if (message.getData().getBoolean(ApiParams.PARM_RESPONCE)){
                    ArrayList<HashMap<String,String>> loginArray =  (ArrayList<HashMap<String,String>>) message.getData().getSerializable(ApiParams.PARM_DATA);
                    if(loginArray!=null) {
                        HashMap<String, String> userdata = loginArray.get(0);
                        Intent intent = null;
                        common.setSession(ApiParams.COMMON_KEY, userdata.get("user_id"));
                        common.setSession(ApiParams.USER_FULLNAME, userdata.get("user_fullname"));
                        common.setSession(ApiParams.USER_EMAIL, userdata.get("user_email"));
                        common.setSession(ApiParams.USER_PHONE,userdata.get("user_phone"));
                        intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    common.setToastMessage(message.getData().getString(ApiParams.PARM_ERROR));
                }
            }


        }
    };
}
