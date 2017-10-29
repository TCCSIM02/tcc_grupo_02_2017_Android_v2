package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doctappo.R;

import models.ModelLogin;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CadastroUsuario.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CadastroUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroUsuario extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText edtNomeUsuario, edtSenha, edtConfirmaSenha;
    Button btnCriarPaciente;
    public CadastroUsuario() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view4 = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);

        edtNomeUsuario = (EditText) view4.findViewById(R.id.txtNomeUsuario);
        edtSenha = (EditText) view4.findViewById(R.id.txtSenha);
        edtConfirmaSenha = (EditText) view4.findViewById(R.id.txtConfirmaSenha);

        btnCriarPaciente = (Button) view4.findViewById(R.id.btnCriarPaciente);

        btnCriarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Login:","CHEGAMOS AQUI 7");
                cadastrar();
            }
        });
        return view4;
    }

    public void cadastrar(){

        if(!edtSenha.getText().toString().equals(edtConfirmaSenha.getText().toString())){
            Toast.makeText(getActivity().getBaseContext(), "A senha e confirmação de senha precisam ser iguais", Toast.LENGTH_LONG).show();
            Log.e("Login:","CHEGAMOS AQUI 8");
        }else if(buscaUsuarioExistente()){
            Log.e("Login:","CHEGAMOS AQUI 9");
            Toast.makeText(getActivity().getBaseContext(), "Este nome de usuário já está cadastrado em nosso banco de dados, por favor, escolher um novo", Toast.LENGTH_LONG).show();
        }else{
            Log.e("Login:","CHEGAMOS AQUI 9.5");
        }

    }

    public boolean buscaUsuarioExistente(){

        ModelLogin modelLogin = new ModelLogin();
        boolean existeUsuario = true;

        try {
            Log.e("Login:","CHEGAMOS AQUI 9.1");
            if(modelLogin.listarLogin(edtNomeUsuario.getText().toString()).size() > 0){
                Log.e("Login:","CHEGAMOS AQUI 9.2");
            }else{
                Log.e("Login:","CHEGAMOS AQUI 9.3");
                existeUsuario = false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e("Login:","CHEGAMOS AQUI 9.4");
        }
        Log.e("Login:","CHEGAMOS AQUI 9.41 " + existeUsuario);
        return existeUsuario;
    }

}
