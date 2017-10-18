package com.doctappo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import models.ModelEspecialidade;

public class CriarAgendamentoActivity extends CommonActivity {

    EditText dataAgendamento;
    DatePickerDialog datePickerDialog;
    TextView txtUnidade;
    Spinner spinnerEspecialidade, spinnerMedico, spinnerHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_agendamento);
        allowBack();
        setHeaderTitle("Agende");

        //final int codUnidade = Integer.parseInt(this.getIntent().getStringExtra("codUnidade"));
        txtUnidade = (TextView)  findViewById(R.id.txtUnidade);

        txtUnidade.setText(this.getIntent().getStringExtra("nomeFantasiaUnidade"));

        preencheEspecialidade();

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
                "Horários disponíveis:"
                ,"00:00","01:00"
                ,"02:00","03:00","04:00"
                ,"05:00","06:00","07:00"
                ,"08:00","09:00","10:00"
                ,"11:00","12:00","13:00"
                ,"14:00","15:00","16:00"
                ,"17:00","18:00","19:00"
                ,"20:00","21:00","22:00"
                ,"23:00"}){

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
                        next();
                    }
                }
        );
    }

    public void next(){
        Intent intent = new Intent(CriarAgendamentoActivity.this, ConfirmarAgendamentoActivity.class);
        intent.putExtra("nomeFantasiaUnidade",txtUnidade.getText());
        intent.putExtra("spinnerEspecialidade",spinnerEspecialidade.getSelectedItem().toString());
        intent.putExtra("spinnerMedico",spinnerMedico.getSelectedItem().toString());
        intent.putExtra("dataAgendamento",String.valueOf(dataAgendamento.getText()));
        intent.putExtra("spinnerHorario",spinnerHorario.getSelectedItem().toString());
        Log.v("", String.valueOf(dataAgendamento.getText()));
        startActivity(intent);
    }

    public void preencheEspecialidade(){
        ModelEspecialidade modelEspecialidade = new ModelEspecialidade();

        int tamanho = 0;
        try {
            tamanho = modelEspecialidade.listarEspecialidadesString().length;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String[] vetorEspecialidade = new String[tamanho+1];
        vetorEspecialidade[0] = "Especialidade:";
        try {
            String[] vetorModel = modelEspecialidade.listarEspecialidadesString();

            for(int i = 1 ; i < vetorEspecialidade.length  ; i++){
                Log.e("ERRRROOO","Especialidade: " + vetorModel[i-1] + "Index: " + i);
                vetorEspecialidade[i] = vetorModel[i-1];
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        spinnerEspecialidade = (Spinner) findViewById(R.id.spnEspecialidades);
        ArrayAdapter<String> adapterEspecialidade = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, vetorEspecialidade){

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

    }
}
