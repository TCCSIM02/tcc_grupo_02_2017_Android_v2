package com.doctappo;

import android.os.Bundle;

public class VisualizarAgendamentoActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_agendamento);
        allowBack();
        setHeaderTitle("Visualizar agendamento");

        isLogged();

    }
}
