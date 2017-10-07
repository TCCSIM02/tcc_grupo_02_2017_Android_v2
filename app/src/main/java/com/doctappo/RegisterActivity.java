package com.doctappo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Config.ApiParams;
import util.BuscaCep;
import util.Mask;

public class RegisterActivity extends CommonActivity {
    EditText editEmail, editSenha, editConfirmaSenha, editCelular, editFullname, editCep, editEndereco, editCpf, editTelPrincipal, editTelOpcional, editDataNascimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        allowBack();
        setHeaderTitle(getString(R.string.register_new));

        editEmail = (EditText)findViewById(R.id.txtEmail);
        editSenha = (EditText)findViewById(R.id.txtSenha);
        editFullname = (EditText)findViewById(R.id.txtFirstname);
        editConfirmaSenha = (EditText)findViewById(R.id.txtConfirmaSenha);

        editDataNascimento = (EditText) findViewById(R.id.txtDataNascimento);
        editDataNascimento.addTextChangedListener(Mask.insert("##/##/####", editDataNascimento));

        editCpf = (EditText) findViewById(R.id.txtCpf);
        editCpf.addTextChangedListener(Mask.insert("###.###.###-##", editCpf));

        editCelular = (EditText)findViewById(R.id.txtCelular);
        editCelular.addTextChangedListener(Mask.insert("(##)####-#####", editCelular));

        editTelPrincipal = (EditText)findViewById(R.id.txtTelPrincipal);
        editTelPrincipal.addTextChangedListener(Mask.insert("(##)####-#####", editTelPrincipal));

        editTelOpcional = (EditText)findViewById(R.id.txtTelOpcional);
        editTelOpcional.addTextChangedListener(Mask.insert("(##)####-#####", editTelOpcional));

        editCep = (EditText)findViewById(R.id.txtCep);
        editCep.addTextChangedListener(Mask.insert("#####-##", editCep));

        Button button = (Button)findViewById(R.id.btnCriarPaciente);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void register(){

        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        String confirmaSenha = editConfirmaSenha.getText().toString();
        //String fullname = editFullname.getText().toString();
        //String phone = editCelular.getText().toString();
        boolean cancel = false;
        View focusView = null;

        String cep = editCep.getText().toString();

        BuscaCep bCep = new BuscaCep();


        try {
            editEndereco.setText(bCep.getEndereco(cep));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(senha)) {
            common.setToastMessage(getString(R.string.valid_required_password));
            focusView = editSenha;
            cancel = true;
        }
        else if (TextUtils.isEmpty(confirmaSenha)) {
            common.setToastMessage(getString(R.string.valid_required_password));
            focusView = editConfirmaSenha;
            cancel = true;
        }

        else if(senha != confirmaSenha ){
            common.setToastMessage("A confirmação de senha precisa ser igual a senha");
            focusView = editConfirmaSenha;
            cancel = true;
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
