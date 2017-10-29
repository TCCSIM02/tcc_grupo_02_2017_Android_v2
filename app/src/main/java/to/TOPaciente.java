package to;

/**
 * Created by Nilton on 05/10/2017.
 */

public class TOPaciente extends TOUsuario {

    private int codPaciente;
    private String numConvenio, alergiaMedicamento, alergiaAlimentares, medicamentoContinuo, cirurgia, antecedentesPessoais, tipoSanguineo;;
    private Double peso, altura;
    /**
     * @return the codPaciente
     */
    public int getCodPaciente() {
        return codPaciente;
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

    /**
     * @return the numConvenio
     */
    public String getNumConvenio() {
        return numConvenio;
    }
    /**
     * @param codPaciente the codPaciente to set
     */
    public void setCodPaciente(int codPaciente) {
        this.codPaciente = codPaciente;
    }
    /**
     * @param numConvenio the numConvenio to set
     */
    public void setNumConvenio(String numConvenio) {
        this.numConvenio = numConvenio;
    }

}

