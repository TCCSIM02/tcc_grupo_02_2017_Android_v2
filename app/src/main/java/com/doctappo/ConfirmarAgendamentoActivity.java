package com.doctappo;

import android.os.Bundle;
import android.widget.TextView;

public class ConfirmarAgendamentoActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_agendamento);
        allowBack();
        setHeaderTitle("Confirmar agendamento");

        //int codUnidade = Integer.parseInt(this.getIntent().getStringExtra("codUnidade"));

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

        
    }
}
