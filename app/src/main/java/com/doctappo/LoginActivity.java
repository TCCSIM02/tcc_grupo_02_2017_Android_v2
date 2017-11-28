package com.doctappo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import models.ModelLogin;


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
            //common.setToastMessage(getString(R.string.valid_required_email));
            focusView = editEmail;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {

            Toast.makeText(this, getString(R.string.valid_required_password),
                    Toast.LENGTH_LONG).show();
            //common.setToastMessage(getString(R.string.valid_required_password));
            focusView = editPassword;
            cancel = true;
        }

        Log.e("Login:","CHEGAMOS AQUI");
        if( !cancel) {

            ModelLogin modelLogin = new ModelLogin(email,password);
            Log.e("Login:","CHEGAMOS AQUI 2");
            try {

                Log.e("Login:","CHEGAMOS AQUI 3");
                if(modelLogin.logar()){

                    Log.e("Login:","CHEGAMOS AQUI 4");

                    Log.e("Login:","LOGADO");

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("logado","true");
                    intent.putExtra("codLogin",String.valueOf(modelLogin.getCodLogin()));
                    intent.putExtra("nomeLogin",String.valueOf(modelLogin.getNomeLogin()));
                    startActivity(intent);

                }else{
                    Toast.makeText(this, "DEU PAU",
                            Toast.LENGTH_LONG).show();
                    Log.e("Login:","DEU PAU");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public void forgotPassword(View view){
        String email = editEmail.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            //common.setToastMessage(getString(R.string.valid_required_email));
            focusView = editEmail;
            cancel = true;
        }
        if (!isValidEmail(email)) {
            //common.setToastMessage(getString(R.string.valid_email));
            focusView = editEmail;
            cancel = true;
        }
        if (cancel) {
            if (focusView!=null)
                focusView.requestFocus();
        } else {
            HashMap<String,String> params = new HashMap<>();

            params.put("user_email",email);

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
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
        finish();
    }

}
