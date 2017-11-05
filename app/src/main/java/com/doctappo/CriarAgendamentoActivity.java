package com.doctappo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import models.ModelAgendamento;
import models.ModelEspecialidade;
import models.ModelMedico;

public class CriarAgendamentoActivity extends CommonActivity {

    EditText dataAgendamento;
    DatePickerDialog datePickerDialog;
    TextView txtUnidade;
    Spinner spinnerEspecialidade, spinnerMedico, spinnerHorario;
    Button btnAvancar;
    int codUnidade, codMedico, codEspecialidade;
    ArrayList<Integer> buscaCodMedicos = new ArrayList<Integer>();
    ArrayList<Integer> buscaCodEspecialidade = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_agendamento);
        allowBack();
        setHeaderTitle("Agende");

        codUnidade = Integer.parseInt(this.getIntent().getStringExtra("codUnidade"));
        txtUnidade = (TextView)  findViewById(R.id.txtUnidade);

        txtUnidade.setText(this.getIntent().getStringExtra("nomeFantasiaUnidade"));

        isLogged();

        spinnerEspecialidade = (Spinner) findViewById(R.id.spnEspecialidades);
        spinnerMedico = (Spinner) findViewById(R.id.spnMedico);
        spinnerHorario = (Spinner) findViewById(R.id.spnHorario);
        preencheEspecialidade();

        spinnerEspecialidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(!spinnerEspecialidade.getSelectedItem().toString().equals("Especialidade:")){
                    preencherMedico();
                    dataAgendamento.setVisibility(View.VISIBLE);
                    int posEsp = spinnerEspecialidade.getSelectedItemPosition()-1;
                    codEspecialidade = buscaCodEspecialidade.get(posEsp);
                    Log.e("codEspecialidade",buscaCodEspecialidade.size()+ " "+ codEspecialidade);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

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
                                dataAgendamento.setText(String.format("%02d", dayOfMonth)+ "/"
                                        +  String.format("%02d",(monthOfYear+1)) + "/" + year);
                                Log.e("buscaCodMedicos",""+ buscaCodMedicos.size());
                                Log.e("spinnerMedico",""+ spinnerMedico.getSelectedItemPosition());
                                int pos = spinnerMedico.getSelectedItemPosition()-1;
                                Log.e("spinnerMedico",""+ pos);
                                codMedico = buscaCodMedicos.get(pos);
                                Log.e("codMedico",buscaCodMedicos.size()+" "+ codMedico);
                                preencheHorario();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnAvancar = (Button)  findViewById(R.id.btnAvancarAgendamento);

        spinnerHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(!spinnerHorario.getSelectedItem().toString().equals("Horários disponíveis:")){
                    btnAvancar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

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
        intent.putExtra("codMedico",String.valueOf(codMedico));
        intent.putExtra("codEspecialidade",String.valueOf(codEspecialidade));
        intent.putExtra("codLogin",super.codLogin);
        intent.putExtra("codUnidade",String.valueOf(codUnidade));
        intent.putExtra("logado","true");
        Log.v("", String.valueOf(dataAgendamento.getText()));
        startActivity(intent);
    }

    public void preencheEspecialidade(){
        ModelEspecialidade modelEspecialidade = new ModelEspecialidade();

        int tamanho = 0;

        //codUnidade;
        try {
            tamanho = modelEspecialidade.listarEspecialidadesString(codUnidade).length;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String[] vetorEspecialidade = new String[tamanho+1];
        vetorEspecialidade[0] = "Especialidade:";
        try {
            String[][] matrizModel = modelEspecialidade.listarEspecialidadesString(codUnidade);

            for(int i = 1 ; i < vetorEspecialidade.length  ; i++){
                vetorEspecialidade[i] = matrizModel[i-1][0];
                buscaCodEspecialidade.add(Integer.parseInt(matrizModel[i-1][1]));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


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
    
    public void preencherMedico(){
        ModelMedico modelMedico = new ModelMedico();

        int tamanho = 0;

        //codUnidade;
        Log.e("spinnerEspecialidade",spinnerEspecialidade.getSelectedItem().toString());
        //Log.e("spinnerMedico",spinnerMedico.getSelectedItemPosition()+"nilton");
        try {
            tamanho = modelMedico.listarMedicoEspecialidadeUnidade(codUnidade,spinnerEspecialidade.getSelectedItem().toString()).size();
            Log.e("CEhagemos",tamanho+"");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String[] vetorMedico = new String[tamanho+1];
        vetorMedico[0] = "Médico:";
        try {
            Log.e("CEhagemos 2","2");
            String[][] matrizModelMedico = modelMedico.listarMedicoEspecialidadeUnidadeString(codUnidade,spinnerEspecialidade.getSelectedItem().toString());
            Log.e("CEhagemos 2",matrizModelMedico.length+"");
            for(int i = 1 ; i <= matrizModelMedico.length ; i++){
                vetorMedico[i] = matrizModelMedico[i-1][0];
                Log.e("CEhagemos 3",vetorMedico[i]+"");
                Log.e("CEhagemos 4",matrizModelMedico[i-1][1]+"");
                buscaCodMedicos.add(Integer.parseInt(matrizModelMedico[i-1][1]));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapterMedico = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, vetorMedico){

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

        spinnerMedico.setAdapter(adapterMedico);
        spinnerMedico.setVisibility(View.VISIBLE);
    }

    public void preencheHorario() {
        ModelAgendamento modelAgendamento = new ModelAgendamento();

        String data = "";
        String dataOriginal = "";
        Log.e("DATA", dataAgendamento.getText().toString());
        try {
            dataOriginal = dataAgendamento.getText().toString().replace("/","");
            data = dataOriginal.substring(4,8) + "-" + dataOriginal.substring(2,4) + "-" + dataOriginal.substring(0,2);
        } catch (Exception e) {

        }
        Log.e("DATA", data);

        String[] horas = new String[]{
                "Horários disponíveis:"
                ,"00:00","01:00"
                ,"02:00","03:00","04:00"
                ,"05:00","06:00","07:00"
                ,"08:00","09:00","10:00"
                ,"11:00","12:00","13:00"
                ,"14:00","15:00","16:00"
                ,"17:00","18:00","19:00"
                ,"20:00","21:00","22:00"
                ,"23:00"};

        ArrayList<String> horasSpinner = new ArrayList<String>();
        ArrayList<String> horariosOcupados = null;
        horasSpinner.add("Horários disponíveis:");
        Log.e("DATA", "CHEGAMOS AQUI 30");
        try {
            Log.e("DATA", "CHEGAMOS AQUI 31");
            horariosOcupados = modelAgendamento.horariosOcupados(codMedico,data);
        } catch (ParseException e) {

        }

        for(int i = 1 ; i < horas.length ; i++ ){
            if( horariosOcupados.size() > 0){
                for(int j = 0 ; j < horariosOcupados.size(); j++){
                    if(!horas[i].equals(horariosOcupados.get(j))){
                        horasSpinner.add(horas[i]);
                    }
                }
            }else{
                horasSpinner.add(horas[i]);
            }
        }

        ArrayAdapter<String> adpterHorario = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, horasSpinner.toArray(new String[horasSpinner.size()])){

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
        spinnerHorario.setVisibility(View.VISIBLE);
    }

}
