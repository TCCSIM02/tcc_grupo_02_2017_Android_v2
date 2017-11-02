package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

    Button btnCriarPaciente;
    EditText edtNomeUsuario, edtSenha, edtConfirmaSenha;
    EditText edtCpf, edtDataNascimento, edtNome, edtNrConvenio;
    EditText edtCep, edtEndereco, edtCidade, edtUf, edtNumero;
    EditText edtEmail, edtTelPrincipal, edtTelOpcional, edtCelular;
    Spinner spinnerEstadoCivil;

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

        spinnerEstadoCivil = (Spinner) inflater.inflate(R.layout.fragment_cadastro_pessoal, container, false).findViewById(R.id.spnFragEstadoCivil);
        edtNome = (EditText) inflater.inflate(R.layout.fragment_cadastro_pessoal, container, false).findViewById(R.id.txtFirstname);
        edtNrConvenio = (EditText) inflater.inflate(R.layout.fragment_cadastro_pessoal, container, false).findViewById(R.id.txtNrConvenio);
        edtCpf = (EditText) inflater.inflate(R.layout.fragment_cadastro_pessoal, container, false).findViewById(R.id.txtCpf);
        edtDataNascimento = (EditText) inflater.inflate(R.layout.fragment_cadastro_pessoal, container, false).findViewById(R.id.txtDataNascimento);
        edtCep = (EditText) inflater.inflate(R.layout.fragment_cadastro_endereco, container, false).findViewById(R.id.txtCep);
        edtEndereco = (EditText) inflater.inflate(R.layout.fragment_cadastro_endereco, container, false).findViewById(R.id.txtEndereco);
        edtCidade = (EditText) inflater.inflate(R.layout.fragment_cadastro_endereco, container, false).findViewById(R.id.txtCidade);
        edtUf = (EditText) inflater.inflate(R.layout.fragment_cadastro_endereco, container, false).findViewById(R.id.txtUF);
        edtNumero = (EditText) inflater.inflate(R.layout.fragment_cadastro_endereco, container, false).findViewById(R.id.txtNumeroEndereco);
        edtEmail = (EditText) inflater.inflate(R.layout.fragment_cadastro_contato, container, false).findViewById(R.id.txtEmailCont);
        edtTelPrincipal = (EditText) inflater.inflate(R.layout.fragment_cadastro_contato, container, false).findViewById(R.id.txtTelPrincipal);
        edtTelOpcional = (EditText) inflater.inflate(R.layout.fragment_cadastro_contato, container, false).findViewById(R.id.txtTelOpcional);
        edtCelular = (EditText) inflater.inflate(R.layout.fragment_cadastro_contato, container, false).findViewById(R.id.txtCelular);

        btnCriarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.e("Login:","CHEGAMOS AQUI 7");
            cadastrar();
            Toast.makeText(getActivity().getBaseContext(),edtEmail.getText().toString(), Toast.LENGTH_LONG).show();
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
