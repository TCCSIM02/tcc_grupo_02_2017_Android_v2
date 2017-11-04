package com.doctappo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarAgendamentoActivity extends CommonActivity {

    int codMedico, codEspecialidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_agendamento);
        allowBack();
        setHeaderTitle("Confirmar agendamento");

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

        Button btnCancelar = (Button)  findViewById(R.id.btnCancelar);

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
    }
}
