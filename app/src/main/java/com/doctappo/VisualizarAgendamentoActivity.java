package com.doctappo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;

import models.ModelAgendamento;

public class VisualizarAgendamentoActivity extends CommonActivity {

    int codAgendamento;
    Button btnCancelarAgendamento;
    private AlertDialog alerta;
    String codLogin2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_agendamento);
        allowBack();
        setHeaderTitle("Visualizar agendamento");

        super.isLogged();

        TextView txtUnidade = (TextView)findViewById(R.id.txtUnidadeVis);
        TextView txtEspecialidade = (TextView) findViewById(R.id.txtEspecialidadeVis);
        TextView txtMedico = (TextView) findViewById(R.id.txtMedicoVis);
        TextView txtDataAgendamento = (TextView)findViewById(R.id.txtDataAgendamentoVis);
        TextView txtHorario =  (TextView) findViewById(R.id.txtHorarioVis);

        codAgendamento = Integer.parseInt(this.getIntent().getStringExtra("codAgendamento"));
        Log.e("CodLogin",super.codLogin);
        codLogin2 = super.codLogin;

        ArrayList agendamento = new ArrayList();
        ModelAgendamento modelAgendamento = new ModelAgendamento();

        try {
            agendamento = modelAgendamento.visualizarAgendamento(codAgendamento);
            txtUnidade.setText((CharSequence) agendamento.get(0));
            txtEspecialidade.setText((CharSequence) agendamento.get(3));
            txtMedico.setText((CharSequence) agendamento.get(1));
            txtDataAgendamento.setText((CharSequence) agendamento.get(2));
            txtHorario.setText((CharSequence) agendamento.get(2));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        btnCancelarAgendamento = (Button)  findViewById(R.id.btnDesmarcar);

        btnCancelarAgendamento.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelarAgendamento();
                    }
                }
        );
    }

    private void cancelarAgendamento() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Desmarcar");
        //define a mensagem
        builder.setMessage("Deseja realmente desmarcar o agendamento?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                ModelAgendamento modelAgendamento = new ModelAgendamento();
                modelAgendamento.setCodAgendamento(codAgendamento);
                modelAgendamento.cancelarAgendamento();
                Intent intent = new Intent(VisualizarAgendamentoActivity.this, MeusAgendamentosActivity.class);
                intent.putExtra("codLogin",codLogin2);
                Log.e("CodLogin2",codLogin2);
                intent.putExtra("logado","true");
                startActivity(intent);
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}
