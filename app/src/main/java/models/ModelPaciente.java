package models;

/**
 * Created by Nilton on 05/10/2017.
 */

import java.util.ArrayList;
import java.util.Date;

import dao.DAOPaciente;
import to.TOPaciente;

public class ModelPaciente extends ModelUsuario {

    private int codPaciente;
    public int codLoginCadastrado;
    private String numConvenio, alergiaMedicamento, alergiaAlimentares, medicamentoContinuo, cirurgia, antecedentesPessoais, tipoSanguineo;;
    private Double peso, altura;

    public ModelPaciente() {
        // TODO Auto-generated constructor stub
    }

    public String getAlergiaMedicamento() {
        return alergiaMedicamento;
    }

    public void setAlergiaMedicamento(String alergiaMedicamento) {
        this.alergiaMedicamento = alergiaMedicamento;
    }

    public String getAlergiaAlimentares() {
        return alergiaAlimentares;
    }

    public void setAlergiaAlimentares(String alergiaAlimentares) {
        this.alergiaAlimentares = alergiaAlimentares;
    }

    public String getMedicamentoContinuo() {
        return medicamentoContinuo;
    }

    public void setMedicamentoContinuo(String medicamentoContinuo) {
        this.medicamentoContinuo = medicamentoContinuo;
    }

    public String getCirurgia() {
        return cirurgia;
    }

    public void setCirurgia(String cirurgia) {
        this.cirurgia = cirurgia;
    }

    public String getAntecedentesPessoais() {
        return antecedentesPessoais;
    }

    public void setAntecedentesPessoais(String antecedentesPessoais) {
        this.antecedentesPessoais = antecedentesPessoais;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public ModelPaciente(int codPaciente, String numConvenio) {
        super();
        this.codPaciente = codPaciente;
        this.numConvenio = numConvenio;
    }

    public int getCodLoginCadastrado() {
        return codLoginCadastrado;
    }

    public void setCodLoginCadastrado(int codLoginCadastrado) {
        this.codLoginCadastrado = codLoginCadastrado;
    }

    public ModelPaciente(String numeroEndereco, Date dataCadastro, String nome,
                         String cpf, Date dataNascimento, String estadoCivil, String email,
                         String nacionalidade, String endereco, String cep, String cidade,
                         String uf, String pais, String tel1, String tel2, String cel,
                         String flagAtivo, int codPaciente, String numConvenio) {
        super(numeroEndereco, dataCadastro, nome, cpf, dataNascimento,
                estadoCivil, email, nacionalidade, endereco, cep, cidade, uf, pais,
                tel1, tel2, cel, flagAtivo);

        this.codPaciente = codPaciente;
        this.numConvenio = numConvenio;
    }


    public ModelPaciente(int codPaciente) {
        super();
        this.codPaciente = codPaciente;
    }


    public int getCodPaciente() {
        return codPaciente;
    }


    public String getNumConvenio() {
        return numConvenio;
    }


    public void setCodPaciente(int codPaciente) {
        this.codPaciente = codPaciente;
    }


    public void setNumConvenio(String numConvenio) {
        this.numConvenio = numConvenio;
    }

    public TOPaciente getTO(){

        TOPaciente toPaciente = new TOPaciente();

		/*TOUsuario*/
        toPaciente.setNumeroEndereco(super.getNumeroEndereco()) ;
        toPaciente.setDataCadastro(super.getDataCadastro())  ;
        toPaciente.setNome(super.getNome());
        toPaciente.setCpf(super.getCpf());
        toPaciente.setDataNascimento(super.getDataNascimento()) ;
        toPaciente.setEstadoCivil(super.getEstadoCivil()) ;
        toPaciente.setEmail(super.getEmail()) ;
        toPaciente.setNacionalidade(super.getNacionalidade());
        toPaciente.setEndereco(super.getEndereco());
        toPaciente.setCep(super.getCep()) ;
        toPaciente.setCidade(super.getCidade()) ;
        toPaciente.setUf(super.getUf()) ;
        toPaciente.setPais(super.getPais()) ;
        toPaciente.setTel1(super.getTel1());
        toPaciente.setTel2(super.getTel2());
        toPaciente.setCel(super.getCel()) ;
        toPaciente.setFlagAtivo(super.getFlagAtivo());

		/*TOPaciente*/
        toPaciente.setCodPaciente(getCodPaciente());
        toPaciente.setNumConvenio(getNumConvenio());

        return toPaciente;
    }

    public void cadastrarPaciente(){
        DAOPaciente dao = new DAOPaciente();
        TOPaciente toPaciente = getTO();
        dao.cadastrarPaciente(toPaciente,codLoginCadastrado);
        this.codPaciente = toPaciente.getCodPaciente();
    }

    public void alterarPaciente(){

    }

    public void excluirPaciente(){

    }

    public void consultarPacienteCod() throws ClassNotFoundException  {

    }


    public ArrayList<TOPaciente> listarPacientes() throws ClassNotFoundException{
        ArrayList<TOPaciente> lista = null;

        return lista;
    }

    public ArrayList<TOPaciente> listarPacientes(String chave) throws ClassNotFoundException{
        ArrayList<TOPaciente> lista = null;


        return lista;
    }

}
