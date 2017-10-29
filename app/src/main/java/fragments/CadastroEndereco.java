package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.doctappo.R;

import org.json.JSONException;

import util.Mask;
import util.ViaCEP;
import util.ViaCEPException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CadastroEndereco.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CadastroEndereco extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText edtCep, edtEndereco, edtCidade, edtUf, edtNumero;
    ImageView btnCep;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CadastroEndereco() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_endereco, container, false);

        // Inflate the layout for this fragment
        edtCep = (EditText) view.findViewById(R.id.txtCep);
        edtEndereco = (EditText) view.findViewById(R.id.txtEndereco);
        edtCidade = (EditText) view.findViewById(R.id.txtCidade);
        edtUf = (EditText) view.findViewById(R.id.txtUF);
        edtNumero = (EditText) view.findViewById(R.id.txtNumeroEndereco);
        btnCep = (ImageView) view.findViewById(R.id.btnBuscaCEP);

        edtCep.addTextChangedListener(Mask.insert("#####-###", edtCep));



        Log.e("Chegamos", "AQUI");

        btnCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Chegamos", "AQUI 2 ");

                if (edtCep.equals("")){
                    Toast.makeText(getActivity().getBaseContext(), "Insira um CEP", Toast.LENGTH_LONG).show();
                }else{
                    buscarCep();
                }

            }
        });

        return view;
    }

    public void buscarCep(){
        ViaCEP viaCEP = null;

        try {
            viaCEP = new ViaCEP(edtCep.getText().toString());
            edtEndereco.setText(viaCEP.getLogradouro());
            edtEndereco.setVisibility(View.VISIBLE);
            edtCidade.setText(viaCEP.getLocalidade());
            edtCidade.setVisibility(View.VISIBLE);
            edtUf.setText(viaCEP.getUf());
            edtUf.setVisibility(View.VISIBLE);
            edtNumero.setHint("Número:");
            edtNumero.setVisibility(View.VISIBLE);


        } catch (ViaCEPException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getBaseContext(), "Insira um CEP válido", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getBaseContext(), "Insira um CEP válido", Toast.LENGTH_LONG).show();
        }
    }

}
