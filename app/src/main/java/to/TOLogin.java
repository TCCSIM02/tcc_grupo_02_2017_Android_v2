package to;

/**
 * Created by Nilton on 07/10/2017.
 */

import java.util.Date;

public class TOLogin {

    public int codLogin;
    public String nomeLogin, email, senha, flagAtivo,senhaCriptografada;
    /**
     * @return the senhaCriptografada
     */
    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }
    /**
     * @param senhaCriptografada the senhaCriptografada to set
     */
    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }
    public Date dataCadastro;
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


}
