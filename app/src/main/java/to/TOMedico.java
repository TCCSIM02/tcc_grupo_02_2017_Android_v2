package to;

public class TOMedico extends TOUsuario {
	private int codMedico;
	private String crm, cro;
	/**
	 * @return the codMedico
	 */
	public int getCodMedico() {
		return codMedico;
	}
	/**
	 * @return the crm
	 */
	public String getCrm() {
		return crm;
	}
	/**
	 * @return the cro
	 */
	public String getCro() {
		return cro;
	}
	/**
	 * @param codMedico the codMedico to set
	 */
	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
	}
	/**
	 * @param crm the crm to set
	 */
	public void setCrm(String crm) {
		this.crm = crm;
	}
	/**
	 * @param cro the cro to set
	 */
	public void setCro(String cro) {
		this.cro = cro;
	}
	
}
