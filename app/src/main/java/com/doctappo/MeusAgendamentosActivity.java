package com.doctappo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;

import models.ModelAgendamento;

public class MeusAgendamentosActivity extends CommonActivity {
    String codLogin2 = "";
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

        Log.e("CodLogin",super.codLogin);
        codLogin2 = super.codLogin;
        Log.e("CodLogin2",codLogin2);
        try {
            tamanho  = modelAgendamento.meusAgendamentos(Integer.parseInt(super.codLogin)).size();
            Log.e("CodLoginTamanho",tamanho + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("CodLoginTamanho",tamanho + "");

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

                //String.valueOf(position);
                Intent intent = new Intent(MeusAgendamentosActivity.this, VisualizarAgendamentoActivity.class);
                intent.putExtra("codAgendamento",idAgendamento);
                intent.putExtra("codLogin",codLogin2);
                Log.e("CodLogin2",codLogin2);
                intent.putExtra("logado","true");
                startActivity(intent);
            }
        });
    }
}