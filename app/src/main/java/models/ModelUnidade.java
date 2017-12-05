package models;

/**
 * Created by Nilton on 08/10/2017.
 */

import java.util.ArrayList;
import java.util.Date;

import dao.DAOUnidade;
import to.TOUnidade;

public class ModelUnidade {

    private int codUnidade;
    private String razaoSocial, nomeFantasia, cnpj, nomeRede, endereco, cep, cidade, uf, pais, numeroEndereco, representante, tel1, tel2, cel, flagAtivo;
    private Date dataCadastro;
    private Double latitude, longitude;

    public ModelUnidade() {
        // TODO Auto-generated constructor stub
    }

    public ModelUnidade(int codUnidade, String razaoSocial,
                        String nomeFantasia, String cnpj, String nomeRede, String endereco,
                        String cep, String cidade, String uf,
                        String pais, String numeroEndereco, String representante, String tel1, String tel2,
                        String cel, String flagAtivo, Date dataCadastro, Double latitude, Double longitude) {
        this.codUnidade = codUnidade;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.nomeRede = nomeRede;
        this.endereco = endereco;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.pais = pais;
        this.numeroEndereco = numeroEndereco;
        this.representante = representante;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.cel = cel;
        this.flagAtivo = flagAtivo;
        this.dataCadastro = dataCadastro;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @param codUnidade
     */
    public ModelUnidade(int codUnidade) {
        this.codUnidade = codUnidade;
    }

    /**
     * @return the codUnidade
     */
    public int getCodUnidade() {
        return codUnidade;
    }

    /**
     * @return the razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @return the nomeFantasia
     */
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @return the nomeRede
     */
    public String getNomeRede() {
        return nomeRede;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    /**
     * @return the representante
     */
    public String getRepresentante() {
        return representante;
    }

    /**
     * @return the tel1
     */
    public String getTel1() {
        return tel1;
    }

    /**
     * @return the tel2
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * @return the cel
     */
    public String getCel() {
        return cel;
    }

    /**
     * @return the flagAtivo
     */
    public String getFlagAtivo() {
        return flagAtivo;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param codUnidade the codUnidade to set
     */
    public void setCodUnidade(int codUnidade) {
        this.codUnidade = codUnidade;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * @param nomeFantasia the nomeFantasia to set
     */
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @param nomeRede the nomeRede to set
     */
    public void setNomeRede(String nomeRede) {
        this.nomeRede = nomeRede;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @param logradouro the logradouro to set
     */


    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }


    /**
     * @param representante the representante to set
     */
    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    /**
     * @param tel1 the tel1 to set
     */
    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    /**
     * @param tel2 the tel2 to set
     */
    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    /**
     * @param cel the cel to set
     */
    public void setCel(String cel) {
        this.cel = cel;
    }

    /**
     * @param flagAtivo the flagAtivo to set
     */
    public void setFlagAtivo(String flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public TOUnidade getTO(){

        TOUnidade toUnidade = new TOUnidade();

        toUnidade.setCel(cel);
        toUnidade.setCep(cep);
        toUnidade.setCidade(cidade);
        toUnidade.setCnpj(cnpj);
        toUnidade.setCodUnidade(codUnidade);
        toUnidade.setDataCadastro(dataCadastro);
        toUnidade.setEndereco(endereco);
        toUnidade.setFlagAtivo(flagAtivo);
        toUnidade.setNomeFantasia(nomeFantasia);
        toUnidade.setNomeRede(nomeRede);
        toUnidade.setPais(pais);
        toUnidade.setNumeroEndereco(numeroEndereco);
        toUnidade.setRazaoSocial(razaoSocial);
        toUnidade.setRepresentante(representante);
        toUnidade.setTel1(tel1);
        toUnidade.setTel2(tel2);
        toUnidade.setUf(uf);
        toUnidade.setLatitude(latitude);
        toUnidade.setLongitude(longitude);

        return toUnidade;

    }

    public void cadastrarUnidade(){

        DAOUnidade dao = new DAOUnidade();
        TOUnidade toUnidade = getTO();
        dao.cadastrarUnidade(toUnidade);
        this.codUnidade = toUnidade.getCodUnidade();

    }

    public void alterarUnidade(){
        DAOUnidade dao = new DAOUnidade();
        TOUnidade toUnidade = getTO();
        dao.alterarUnidade(toUnidade);
    }

    public void excluirUnidade(){
        DAOUnidade dao = new DAOUnidade();
        TOUnidade toUnidade = new TOUnidade();
        toUnidade.setCodUnidade(codUnidade);
        dao.excluirUnidade(toUnidade);
    }

    public void consultarUnidadeCod() throws ClassNotFoundException  {
        DAOUnidade dao = new DAOUnidade();

        TOUnidade toUnidade = dao.consultarUnidadeCod(codUnidade);

        cel = toUnidade.getCel();
        cep = toUnidade.getCep();
        cidade = toUnidade.getCidade();
        cnpj = toUnidade.getCnpj();
        codUnidade = toUnidade.getCodUnidade();
        dataCadastro = toUnidade.getDataCadastro();
        endereco = toUnidade.getEndereco();
        flagAtivo = toUnidade.getFlagAtivo();
        nomeFantasia = toUnidade.getNomeFantasia();
        nomeRede = toUnidade.getNomeRede();
        pais = toUnidade.getPais();
        numeroEndereco = toUnidade.getNumeroEndereco();
        razaoSocial = toUnidade.getRazaoSocial();
        representante = toUnidade.getRepresentante();
        tel1 = toUnidade.getTel1();
        tel2 = toUnidade.getTel2();
        uf = toUnidade.getUf();
        latitude = toUnidade.getLatitude();
        longitude = toUnidade.getLongitude();

    }

    public ArrayList<TOUnidade> listarUnidades() throws ClassNotFoundException{
        ArrayList<TOUnidade> lista;
        DAOUnidade dao = new DAOUnidade();
        lista = dao.listarUnidades();
        return lista;
    }
    public ArrayList<TOUnidade> listarUnidades(String chave) throws ClassNotFoundException{
        ArrayList<TOUnidade> lista;
        DAOUnidade dao = new DAOUnidade();
        lista = dao.listarUnidades(chave);
        return lista;
    }

    public ArrayList<TOUnidade> listarUnidadesMap(int codLogin) throws ClassNotFoundException{
        ArrayList<TOUnidade> lista;
        DAOUnidade dao = new DAOUnidade();
        lista = dao.listarUnidadesMap(codLogin);
        return lista;
    }
}
