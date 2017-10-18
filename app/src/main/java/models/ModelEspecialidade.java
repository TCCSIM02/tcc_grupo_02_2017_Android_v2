package models;

import android.util.Log;

import java.util.ArrayList;

import dao.DAOEspecialidade;
import to.TOEspecialidade;

/**
 * Created by Nilton on 17/10/2017.
 */

public class ModelEspecialidade {

    private int codEspecialidade;
    private String flagAtivo, descricao, especialidade;

    public ModelEspecialidade() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param codEspecialidade
     */
    public ModelEspecialidade(int codEspecialidade) {
        this.codEspecialidade = codEspecialidade;
    }

    /**
     * @param codEspecialidade
     * @param flagAtivo
     * @param descricao
     */
    public ModelEspecialidade(int codEspecialidade,String especialidade, String flagAtivo, String descricao) {
        this.codEspecialidade = codEspecialidade;
        this.flagAtivo = flagAtivo;
        this.descricao = descricao;
        this.especialidade = especialidade;
    }

    /**
     * @return the codEspecialidade
     */
    public int getCodEspecialidade() {
        return codEspecialidade;
    }

    /**
     * @return the flagAtivo
     */
    public String getFlagAtivo() {
        return flagAtivo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * @param codEspecialidade the codEspecialidade to set
     */
    public void setCodEspecialidade(int codEspecialidade) {
        this.codEspecialidade = codEspecialidade;
    }

    /**
     * @param flagAtivo the flagAtivo to set
     */
    public void setFlagAtivo(String flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public TOEspecialidade getTO(){

        TOEspecialidade toEspecialidade = new TOEspecialidade();

        toEspecialidade.setCodEspecialidade(codEspecialidade);
        toEspecialidade.setDescricao(descricao);
        toEspecialidade.setEspecialidade(especialidade);
        toEspecialidade.setFlagAtivo(flagAtivo);

        return toEspecialidade;
    }

    public void cadastrarEspecialidade(){
        DAOEspecialidade dao = new DAOEspecialidade();
        TOEspecialidade toEspecialidade = getTO();
        dao.cadastrarEspecialidade(toEspecialidade);
        this.codEspecialidade = toEspecialidade.getCodEspecialidade();
    }

    public void consultarEspecialidadeCod()throws ClassNotFoundException {

        DAOEspecialidade dao = new DAOEspecialidade();
        TOEspecialidade toEspecialidade = dao.consultarEspecialidadeCod(codEspecialidade);

        codEspecialidade = toEspecialidade.getCodEspecialidade();
        descricao =  toEspecialidade.getDescricao();
        flagAtivo = toEspecialidade.getFlagAtivo();
        especialidade = toEspecialidade.getEspecialidade();
    }

    public ArrayList<TOEspecialidade> listarEspecialidades() throws ClassNotFoundException{
        ArrayList<TOEspecialidade> lista;
        DAOEspecialidade dao = new DAOEspecialidade();
        lista = dao.listarEspecialidades();
        return lista;
    }

    public String[] listarEspecialidadesString() throws ClassNotFoundException{
        ArrayList<TOEspecialidade> lista;
        DAOEspecialidade dao = new DAOEspecialidade();
        lista = dao.listarEspecialidades();

        int tamanho = lista.size();
        String[] listaString = new String[tamanho];

        for(int i = 0 ; i < lista.size() ; i++){
            Log.e("Erro",String.valueOf(i));
            Log.e("Objeto","Especialidade: " + lista.get(i).getEspecialidade());

            listaString[i] = lista.get(i).getEspecialidade();
        }

        return listaString;
    }

    public ArrayList<TOEspecialidade> listarEspecialidades(String chave) throws ClassNotFoundException{
        ArrayList<TOEspecialidade> lista;
        DAOEspecialidade dao = new DAOEspecialidade();
        lista = dao.listarEspecialidades(chave);
        return lista;
    }

}
