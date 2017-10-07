package com.doctappo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import Config.ApiParams;
import models.ModelLogin;
import models.ModelPaciente;
import util.Mask;

public class RegisterActivity extends CommonActivity {


    EditText editNome;
    EditText editDataNascimento;
    EditText editNumeroConvenio;
    EditText editCpf;
    EditText editEstadoCivil;
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

        editNome = (EditText)findViewById(R.id.txtFirstname);
        editNumeroConvenio = (EditText)findViewById(R.id.txtNumeroEndereco);
        editEstadoCivil = (EditText)findViewById(R.id.txtEstadoCivil);
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

        editEndereco = (EditText)findViewById(R.id.txtEndereco);

        Button button = (Button)findViewById(R.id.btnCriarPaciente);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void register(){

        boolean cancel = false;
        View focusView = null;

        String sNome = editNome.getText().toString();
        String sDataNascimento = editDataNascimento.getText().toString();
        String sNumeroConvenio = editNumeroConvenio.getText().toString();
        String sCpf = editCpf.getText().toString();
        String sEstadoCivil = editEstadoCivil.getText().toString();
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
                sCpf, dataNasc,  sEstadoCivil, sEmail,
                "Brasileiro",  sEndereco, sCep,  sCidade,
                sUf,  "Brasil",  sTelPrincipal,  sTelOpcional,  sCelular,
                "1", id,  sNumeroConvenio);

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
