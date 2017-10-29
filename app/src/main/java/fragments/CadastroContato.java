package fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.doctappo.R;

import util.Mask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CadastroContato.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CadastroContato#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroContato extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText edtEmail, edtTelPrincipal, edtTelOpcional, edtCelular;

    final Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            edtEmail.requestFocus(); //voltar o foco para o produto
        }
    };

    public CadastroContato() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view3 = inflater.inflate(R.layout.fragment_cadastro_contato, container, false);

        edtEmail = (EditText) view3.findViewById(R.id.txtEmailCont);
        edtTelPrincipal = (EditText) view3.findViewById(R.id.txtTelPrincipal);
        edtTelOpcional = (EditText) view3.findViewById(R.id.txtTelOpcional);
        edtCelular = (EditText) view3.findViewById(R.id.txtCelular);

        edtCelular.addTextChangedListener(Mask.insert("(##)####-#####", edtCelular));
        edtTelPrincipal.addTextChangedListener(Mask.insert("(##)####-#####", edtTelPrincipal));
        edtTelOpcional.addTextChangedListener(Mask.insert("(##)####-#####", edtTelOpcional));

        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean gainFocus) {
                //onFocus
                if (gainFocus) {
                    //set the row background to a different color

                }
                //onBlur
                else {
                    if(!isValidEmail(edtEmail.getText())){
                        myHandler.sendEmptyMessage(0);
                        Toast.makeText(getActivity().getBaseContext(), "Insira um e-mail correto", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return view3;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
