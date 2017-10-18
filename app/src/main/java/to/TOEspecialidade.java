package to;

/**
 * Created by Nilton on 17/10/2017.
 */
public class TOEspecialidade {
    private int codEspecialidade;
    private String flagAtivo, descricao, especialidade;
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
}
