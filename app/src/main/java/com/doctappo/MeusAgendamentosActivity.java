package com.doctappo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

            tamanho  = modelAgendamento.meusAgendamentos(Integer.parseInt(super.codLogin)).size();
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

                //Toast.makeText(getBaseContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MeusAgendamentosActivity.this, VisualizarAgendamentoActivity.class);
                startActivity(intent);
            }
        });
    }
}