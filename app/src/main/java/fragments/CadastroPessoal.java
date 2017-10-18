package fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.doctappo.R;


public class CadastroPessoal extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public CadastroPessoal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastro_pessoal, container, false);

        // Inflate the layout for this fragment
        Spinner spinnerEstadoCivil = (Spinner) view.findViewById(R.id.spnFragEstadoCivil);

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

        return view;
    }


}
