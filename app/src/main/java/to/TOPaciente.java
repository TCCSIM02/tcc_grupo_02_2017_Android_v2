package to;

/**
 * Created by Nilton on 05/10/2017.
 */

public class TOPaciente extends TOUsuario {

    private int codPaciente;
    private String numConvenio;
    /**
     * @return the codPaciente
     */
    public int getCodPaciente() {
        return codPaciente;
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

