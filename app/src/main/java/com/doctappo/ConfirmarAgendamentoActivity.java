package com.doctappo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.ModelAgendamento;

public class ConfirmarAgendamentoActivity extends CommonActivity {

    int codMedico, codEspecialidade, codUnidade, codLogin;
    String dataComeco;
    String horaComeco;
    String dataHoraComecoS;
    Date dataHoraComeco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_agendamento);
        allowBack();
        setHeaderTitle("Confirmar agendamento");

        isLogged();

        codUnidade = Integer.parseInt(this.getIntent().getStringExtra("codUnidade"));
        codMedico = Integer.parseInt(this.getIntent().getStringExtra("codMedico"));
        codEspecialidade = Integer.parseInt(this.getIntent().getStringExtra("codEspecialidade"));
        codLogin = Integer.parseInt(this.getIntent().getStringExtra("codLogin"));

        TextView txtUnidade = (TextView)findViewById(R.id.txtUnidade);
        TextView txtEspecialidade = (TextView) findViewById(R.id.txtEspecialidade);
        TextView txtMedico = (TextView) findViewById(R.id.txtMedico);
        TextView txtDataAgendamento = (TextView)findViewById(R.id.txtDataAgendamento);
        TextView txtHorario =  (TextView) findViewById(R.id.txtHorario);

        txtUnidade.setText(this.getIntent().getStringExtra("nomeFantasiaUnidade"));
        txtEspecialidade.setText(this.getIntent().getStringExtra("spinnerEspecialidade"));
        txtMedico.setText(this.getIntent().getStringExtra("spinnerMedico"));
        txtDataAgendamento.setText(this.getIntent().getStringExtra("dataAgendamento"));
        txtHorario.setText(this.getIntent().getStringExtra("spinnerHorario"));

        dataComeco = txtDataAgendamento.getText().toString();
        horaComeco = txtHorario.getText().toString() + ":00";

        dataHoraComecoS = dataComeco + " " + horaComeco;

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {
            dataHoraComeco = formato.parse(dataHoraComecoS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.e("dataHoraComecoS",dataHoraComecoS);
        Log.e("dataHoraComeco",dataHoraComeco+"");

        Button btnCancelar = (Button)  findViewById(R.id.btnCancelar);
        Button btnConfirmar = (Button)  findViewById(R.id.btnConfirmarAgendamento);

        btnCancelar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calender class's instance and get current date , month and year from calender
                        Intent intent = new Intent(ConfirmarAgendamentoActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        btnConfirmar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calender class's instance and get current date , month and year from calender
                        confirmar();
                    }
                }
        );
    }

    public void confirmar(){
        ModelAgendamento modelAgendamento = new ModelAgendamento(codLogin, codMedico, codUnidade, codEspecialidade, dataHoraComeco);

        modelAgendamento.cadastrarAgendamento();

        Intent intent = new Intent(ConfirmarAgendamentoActivity.this, MainActivity.class);
        intent.putExtra("codLogin",super.codLogin);
        intent.putExtra("codUnidade",String.valueOf(codUnidade));
        intent.putExtra("logado","true");
        startActivity(intent);
    }
}
