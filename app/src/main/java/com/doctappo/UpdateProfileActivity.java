package com.doctappo;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import Config.ApiParams;
import util.VJsonRequest;

public class UpdateProfileActivity extends CommonActivity {
    EditText editEmail,  editPhone, editFullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        allowBack();
        setHeaderTitle(getString(R.string.update_profile));
        editEmail = (EditText)findViewById(R.id.txtEmail);
        editFullname = (EditText)findViewById(R.id.txtFirstname);
        editPhone = (EditText)findViewById(R.id.txtPhone);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        HashMap<String,String> params = new HashMap<>();
        params.put("user_id",common.get_user_id());

        VJsonRequest vJsonRequest = new VJsonRequest(this, ApiParams.USERDATA_URL,params,
                new VJsonRequest.VJsonResponce(){
                    @Override
                    public void VResponce(String responce) {
                        JSONObject userdata = null;
                        try {
                            userdata = new JSONObject(responce);
                            editEmail.setText(userdata.getString("user_email"));
                            editFullname.setText(userdata.getString("user_fullname"));
                            editPhone.setText(userdata.getString("user_phone"));
                            editEmail.setEnabled(false);
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

    public void register(){

        String fullname = editFullname.getText().toString();
        String phone = editPhone.getText().toString();
        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.

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
            params.put("user_id",common.get_user_id());


            VJsonRequest vJsonRequest = new VJsonRequest(this, ApiParams.UPDATEPROFILE_URL,params,
                    new VJsonRequest.VJsonResponce(){
                        @Override
                        public void VResponce(String responce) {

                            common.setSession(ApiParams.USER_FULLNAME, editFullname.getText().toString());
                            common.setSession(ApiParams.USER_PHONE,editPhone.getText().toString());


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

}
