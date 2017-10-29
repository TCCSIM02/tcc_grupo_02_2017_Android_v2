package com.doctappo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CadastroInfoHistoricaActivity extends CommonActivity {

    Spinner spnTipoSanguineo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_info_historica);
        allowBack();
        setHeaderTitle("Informações históricas");

        spnTipoSanguineo = (Spinner) findViewById(R.id.spnTipoSanguineo);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, new String[]{
                "Tipo sanguíneo:","A+","A-","B+","B-","AB+","AB-","O+","O-"}){

            @Override
            public boolean isEnabled(int position){

                if(position == 0){

                    // Disabilita a primeira posição (hint)
                    return false;

                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {

                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if(position == 0){

                    // Deixa o hint com a cor cinza ( efeito de desabilitado)
                    tv.setTextColor(Color.GRAY);

                }else {
                    tv.setTextColor(Color.BLACK);
                }

                return view;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipoSanguineo.setAdapter(dataAdapter);
    }
}
