package com.doctappo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class CriarAgendamentoActivity extends CommonActivity {

    EditText dataAgendamento;
    DatePickerDialog datePickerDialog;
    Spinner spinnerEspecialidade, spinnerMedico, spinnerHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_agendamento);
        allowBack();
        setHeaderTitle("Agende");

        //final int codUnidade = Integer.parseInt(this.getIntent().getStringExtra("codUnidade"));
        final TextView txtUnidade = (TextView)  findViewById(R.id.txtUnidade);

        txtUnidade.setText(this.getIntent().getStringExtra("nomeFantasiaUnidade"));

        spinnerEspecialidade = (Spinner) findViewById(R.id.spnEspecialidades);
        ArrayAdapter<String> adapterEspecialidade = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, new String[]{
                "Especialidade:"
        }){

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

        spinnerEspecialidade.setAdapter(adapterEspecialidade);

        spinnerMedico = (Spinner) findViewById(R.id.spnMedico);
        ArrayAdapter<String> adpterMedico = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, new String[]{
                "Médico:"}){

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

        spinnerMedico.setAdapter(adpterMedico);
        spinnerHorario = (Spinner) findViewById(R.id.spnHorario);

        // initiate the date picker and a button
        dataAgendamento = (EditText) findViewById(R.id.dataAgendamento);
        // perform click event on edit text
        dataAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(CriarAgendamentoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dataAgendamento.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                                spinnerHorario.setVisibility(View.VISIBLE);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        ArrayAdapter<String> adpterHorario = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, new String[]{
                "Horários disponíveis:"}){

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

        spinnerHorario.setAdapter(adpterHorario);

        Button btnAvancar = (Button)  findViewById(R.id.btnAvancarAgendamento);

        btnAvancar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calender class's instance and get current date , month and year from calender
                        Intent intent = new Intent(CriarAgendamentoActivity.this, ConfirmarAgendamentoActivity.class);
                        //intent.putExtra("codUnidade",codUnidade);
                        intent.putExtra("nomeFantasiaUnidade",txtUnidade.getText());
                        intent.putExtra("spinnerEspecialidade",spinnerEspecialidade.getSelectedItem().toString());
                        intent.putExtra("spinnerMedico",spinnerMedico.getSelectedItem().toString());
                        intent.putExtra("dataAgendamento",dataAgendamento.getText());
                        intent.putExtra("spinnerHorario",spinnerHorario.getSelectedItem().toString());
                        startActivity(intent);
                    }
                }
        );


    }
}
