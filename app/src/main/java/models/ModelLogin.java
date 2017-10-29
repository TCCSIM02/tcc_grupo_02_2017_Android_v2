package models;

/**
 * Created by Nilton on 07/10/2017.
 */

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

import dao.DAOLogin;
import to.TOLogin;

public class ModelLogin {

    public int codLogin;
    public String nomeLogin, email, senha, flagAtivo, senhaCriptografada;
    public Date dataCadastro;

    public ModelLogin(){

    }

    /**
     * @param codLogin
     * @param nomeLogin
     * @param email
     * @param senha
     * @param flagAtivo
     * @param dataCadastro
     */
    public ModelLogin(int codLogin, String nomeLogin, String email,
                      String senha, String flagAtivo, Date dataCadastro) {
        super();
        this.codLogin = codLogin;
        this.nomeLogin = nomeLogin;
        this.email = email;
        this.senha = senha;
        this.flagAtivo = flagAtivo;
        this.dataCadastro = dataCadastro;
    }

    /**
     * @param nomeLogin
     * @param senha
     * @param email
     */
    public ModelLogin(String nomeLogin, String email, String senha) {
        super();
        this.nomeLogin = nomeLogin;
        this.senha = senha;
        this.email = email;
    }

    public ModelLogin(String nomeLogin, String senha) {
        super();
        this.nomeLogin = nomeLogin;
        this.senha = senha;
    }

    /**
     * @return the codLogin
     */
    public int getCodLogin() {
        return codLogin;
    }

    /**
     * @return the nomeLogin
     */
    public String getNomeLogin() {
        return nomeLogin;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
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

    /**
     * @param codLogin the codLogin to set
     */
    public void setCodLogin(int codLogin) {
        this.codLogin = codLogin;
    }

    /**
     * @param nomeLogin the nomeLogin to set
     */
    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
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

    public TOLogin getTO() throws NoSuchAlgorithmException, UnsupportedEncodingException{

        TOLogin toLogin = new TOLogin();

        toLogin.setCodLogin(codLogin);
        toLogin.setDataCadastro(dataCadastro);
        toLogin.setFlagAtivo(flagAtivo);
        toLogin.setNomeLogin(nomeLogin);
        toLogin.setEmail(email);
        toLogin.setSenhaCriptografada(getSenhaCriptografada());

        return toLogin;
    }

    public String getSenhaCriptografada() throws NoSuchAlgorithmException, UnsupportedEncodingException{

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }

        String senhaCriptografada = hexString.toString();

        return senhaCriptografada;
    }

    public void cadastrarLogin() throws NoSuchAlgorithmException, UnsupportedEncodingException{

        DAOLogin dao = new DAOLogin();
        TOLogin toLogin = getTO();
        dao.cadastrarLogin(toLogin);
        this.codLogin = toLogin.getCodLogin();

    }

    public boolean logar() throws NoSuchAlgorithmException, UnsupportedEncodingException{

        DAOLogin dao = new DAOLogin();
        TOLogin toLogin = getTO();
        boolean logado = false;

        TOLogin toLoginBD = dao.buscarLogin(toLogin.getNomeLogin());

        String senhaBd = "";

        try {
            senhaBd = toLoginBD.getSenhaCriptografada().toUpperCase();
        }catch (Exception e){

        }

        if (toLogin.getSenhaCriptografada().toUpperCase().equals(senhaBd))
                logado = true;

        return logado;

    }

    public int getCodLoginCadastrado() throws NoSuchAlgorithmException, UnsupportedEncodingException{

        DAOLogin dao = new DAOLogin();
        TOLogin toLogin = getTO();

        return dao.getUltimoCodLogin(toLogin);

    }
    public ArrayList<TOLogin> listarLogin(String chave) throws ClassNotFoundException{
        ArrayList<TOLogin> lista;
        Log.e("Login:","CHEGAMOS AQUI 9.11");
        DAOLogin dao = new DAOLogin();
        Log.e("Login:","CHEGAMOS AQUI 9.12");
        lista = dao.listarLogins(chave);
        Log.e("Login:","CHEGAMOS AQUI 9.13");
        return lista;
    }

}