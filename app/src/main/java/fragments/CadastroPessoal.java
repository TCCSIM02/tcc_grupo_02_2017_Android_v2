package fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doctappo.R;

import java.util.Calendar;

import util.Mask;
import util.ValidaCPF;


public class CadastroPessoal extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public final static int REQUEST_CODE = 65035;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText edtCpf, edtDataNascimento;
    DatePickerDialog datePickerDialog;
    Spinner spinnerEstadoCivil;

    final Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            edtCpf.requestFocus(); //voltar o foco para o produto
        }
    };

    public CadastroPessoal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_cadastro_pessoal, container, false);

        // Inflate the layout for this fragment
        spinnerEstadoCivil = (Spinner) view.findViewById(R.id.spnFragEstadoCivil);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, new String[]{
                "Estado Civil:",
                "Solteiro",
                "Casado",
                "Separado",
                "Divorciado",
                "Viúvo"}){

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
        spinnerEstadoCivil.setAdapter(dataAdapter);

        edtDataNascimento = (EditText) view.findViewById(R.id.txtDataNascimento);

        edtDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                c.set(2000,0,1);
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                edtDataNascimento.setText( String.format("%02d", dayOfMonth)+ "/"
                                        +  String.format("%02d",(monthOfYear+1)) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                checkDrawOverlayPermission();
                datePickerDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                datePickerDialog.show();
            }
        });

        edtCpf = (EditText) view.findViewById(R.id.txtCpf);
        edtCpf.addTextChangedListener(Mask.insert("###.###.###-##", edtCpf));

        edtCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean gainFocus) {
                //onFocus
                if (gainFocus) {
                    //set the row background to a different color

                }
                //onBlur
                else {
                    //set the row background white
                    ValidaCPF valCpf = new ValidaCPF();

                    if(!valCpf.isCPF(edtCpf.getText().toString())){
                        myHandler.sendEmptyMessage(0);
                        Toast.makeText(getActivity().getBaseContext(), "Insira um CPF válido", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return view;
    }

    public void checkDrawOverlayPermission() {
        /** check if we already  have permission to draw over other apps */
        if (!Settings.canDrawOverlays(getActivity().getApplicationContext())) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getActivity().getApplicationContext().getPackageName()));
            /** request permission via start activity for result */
            startActivityForResult(intent, REQUEST_CODE);
        }
    }
}
