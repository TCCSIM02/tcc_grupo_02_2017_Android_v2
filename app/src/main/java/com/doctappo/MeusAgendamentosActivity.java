package com.doctappo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;

import models.ModelAgendamento;

public class MeusAgendamentosActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_agendamentos);
        allowBack();
        setHeaderTitle("Meus agendamentos");

        ListView listview = (ListView) findViewById(R.id.listview);

        super.isLogged();

        ModelAgendamento modelAgendamento = new ModelAgendamento();

        int tamanho = 1000000;

        try {
            int codLogin2 = -1;
            if (!super.codLogin.equals("")) codLogin2 = Integer.parseInt(super.codLogin);
            tamanho  = modelAgendamento.meusAgendamentos(codLogin2).size();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = null;
        try {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, (String[]) modelAgendamento.meusAgendamentos(Integer.parseInt(super.codLogin)).toArray(new String[tamanho]));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        listview.setAdapter(adapter);

        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id) {

                String clicada = (String) adapter.getItemAtPosition(position);
                String idAgendamento = clicada.substring(0,clicada.indexOf(","));
                Toast.makeText(getBaseContext(),clicada.substring(0,clicada.indexOf(",")), Toast.LENGTH_LONG).show();

                //String.valueOf(position);
                Intent intent = new Intent(MeusAgendamentosActivity.this, VisualizarAgendamentoActivity.class);
                intent.putExtra("codAgendamento",idAgendamento);
                //intent.putExtra("codLogin",super.codLogin);
                //intent.putExtra("codUnidade",String.valueOf(codUnidade));
                startActivity(intent);
            }
        });
    }
}