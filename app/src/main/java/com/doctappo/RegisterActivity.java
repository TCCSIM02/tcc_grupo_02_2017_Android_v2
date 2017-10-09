package com.doctappo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import Config.ApiParams;
import models.ModelLogin;
import models.ModelPaciente;
import util.BuscaCep;
import util.Mask;

public class RegisterActivity extends CommonActivity {


    EditText editNome;
    EditText editDataNascimento;
    EditText editNumeroConvenio;
    EditText editCpf;
    Spinner spinnerEstadoCivil;
    EditText editCep;
    EditText editEndereco;
    EditText editCidade;
    EditText editUf;
    EditText editNumeroEndereco;
    EditText editTelPrincipal;
    EditText editTelOpcional;
    EditText editCelular;
    EditText editEmail;
    EditText editNomeUsuario;
    EditText editSenha;
    EditText editConfirmaSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        allowBack();
        setHeaderTitle(getString(R.string.register_new));

        spinnerEstadoCivil = (Spinner) findViewById(R.id.spnEstadoCivil);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, new String[]{
                "Estado Civil:",
                "Solteiro",
                "Casado",
                "Separado",
                "Divorciado",
                "Viúvo"
        });
        spinnerEstadoCivil.setAdapter(adapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editNome = (EditText)findViewById(R.id.txtFirstname);
        editNumeroConvenio = (EditText)findViewById(R.id.txtNumeroEndereco);
        //editEstadoCivil = (EditText)findViewById(R.id.txtEstadoCivil);
        editCidade = (EditText)findViewById(R.id.txtCidade);
        editUf = (EditText)findViewById(R.id.txtUF);
        editNumeroEndereco = (EditText)findViewById(R.id.txtNumeroEndereco);

        editEmail = (EditText)findViewById(R.id.txtEmail);
        editNomeUsuario = (EditText)findViewById(R.id.txtNomeUsuario);
        editSenha = (EditText)findViewById(R.id.txtSenha);
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
        editCep.addTextChangedListener(Mask.insert("#####-###", editCep));

        editEndereco  = (EditText)findViewById(R.id.txtEndereco);

        Button button = (Button)findViewById(R.id.btnCriarPaciente);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void register() {

        boolean cancel = false;
        View focusView = null;

        String sNome = editNome.getText().toString();
        String sDataNascimento = editDataNascimento.getText().toString();
        String sNumeroConvenio = editNumeroConvenio.getText().toString();
        String sCpf = editCpf.getText().toString();
        String sEstadoCivil = spinnerEstadoCivil.getSelectedItem().toString();
        String sCep = editCep.getText().toString();
        String sEndereco = editEndereco.getText().toString();
        String sCidade = editCidade.getText().toString();
        String sUf = editUf.getText().toString();
        String sNumeroEndereco = editNumeroEndereco.getText().toString();
        String sTelPrincipal = editTelPrincipal.getText().toString();
        String sTelOpcional = editTelOpcional.getText().toString();
        String sCelular = editCelular.getText().toString();
        String sEmail = editEmail.getText().toString();
        String sNomeUsuario = editNomeUsuario.getText().toString();
        String sSenha = editSenha.getText().toString();
        String sConfirmaSenha = editConfirmaSenha.getText().toString();

        BuscaCep bCep = new BuscaCep();

        try {
            sEndereco = bCep.getEndereco(sCep);
            sCidade = bCep.getCidade(sCep);
            sUf = bCep.getUF(sCep);

            editEndereco.setText(sEndereco);
            editCidade.setText(sCidade);
            editUf.setText(sUf);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Validação dos campos
        if (TextUtils.isEmpty(sNome)) {
            common.setToastMessage("O campo nome não pode estar vazio");
            focusView = editNome;
            cancel = true;
        }
        else if (TextUtils.isEmpty(sDataNascimento)) {
            common.setToastMessage("O campo data de nascimento não pode estar vazio");
            focusView = editDataNascimento;
            cancel = true;
        }
        else if (TextUtils.isEmpty(sNumeroConvenio)) {
            common.setToastMessage("O número do convênio não pode estar vazio");
            focusView = editNumeroConvenio;
            cancel = true;
        }
        else if (TextUtils.isEmpty(sCpf)) {
            common.setToastMessage("O campo CPF não pode estar vazio");
            focusView = editCpf;
            cancel = true;
        }/*
        else if (TextUtils.isEmpty(sEstadoCivil)) {
            common.setToastMessage("O campo estado civil não pode estar vazio");
            focusView = editEstadoCivil;
            cancel = true;
        }*/
        else if (TextUtils.isEmpty(sCep)) {
            common.setToastMessage("O campo CEP não pode estar vazio");
            focusView = editCep;
            cancel = true;
        }
        else if (TextUtils.isEmpty(sNumeroEndereco)) {
            common.setToastMessage("O campo numero não pode estar vazio");
            focusView = editNumeroEndereco;
            cancel = true;
        }
        else if (TextUtils.isEmpty(sTelPrincipal)) {
            common.setToastMessage("O campo telefone principal não pode estar vazio");
            focusView = editTelPrincipal;
            cancel = true;
        }
        else if (TextUtils.isEmpty(sEmail)){
            common.setToastMessage("O campo email não pode estar vazio");
            focusView = editEmail;
            cancel = true;
        }
        else if (TextUtils.isEmpty(sSenha)) {
            common.setToastMessage("O campo senha não pode estar vazio");
            focusView = editSenha;
            cancel = true;
        }
        else if (TextUtils.isEmpty(sConfirmaSenha)) {
            common.setToastMessage("A confirmação de senha não pode estar vazia");
            focusView = editConfirmaSenha;
            cancel = true;
        }

        else if(!sSenha.equals(sConfirmaSenha) ){
            common.setToastMessage("A confirmação de senha precisa ser igual a senha" + sSenha + " " + sConfirmaSenha);
            focusView = editConfirmaSenha;
            cancel = true;
        }
        else {

            ModelLogin modelLogin = new ModelLogin(sNomeUsuario, sEmail, sSenha);
            try {
                modelLogin.cadastrarLogin();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            int id = -1;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date dataNasc = null;

            try {
                dataNasc = new java.sql.Date(format.parse(sDataNascimento).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            ModelPaciente modelPaciente = new ModelPaciente(
                    sNumeroEndereco, null, sNome,
                    sCpf, dataNasc, sEstadoCivil, sEmail,
                    "Brasileiro", sEndereco, sCep, sCidade,
                    sUf, "Brasil", sTelPrincipal, sTelOpcional, sCelular,
                    "1", id, sNumeroConvenio);

            int codLoginCadastrado = 0;
            try {
                codLoginCadastrado = modelLogin.getCodLoginCadastrado();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            modelPaciente.codLoginCadastrado = codLoginCadastrado;

            modelPaciente.cadastrarPaciente();

            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);

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
