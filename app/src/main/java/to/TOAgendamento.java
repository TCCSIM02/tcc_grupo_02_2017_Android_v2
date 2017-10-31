package to;

import java.util.Date;

public class TOAgendamento {
	private int codAgendamento, codPaciente, codMedico, codUnidade, codAtendente, codEspecialidade;
	private String  flagAtivo;
	private Date dataCadastro, dataHoraComeco, dataHoraFim;
	/**
	 * @return the codAgendamento
	 */
	public int getCodAgendamento() {
		return codAgendamento;
	}
	
	public int getCodPaciente() {
		return codPaciente;
	}
	
	public int getCodMedico() {
		return codMedico;
	}
	
	public int getCodUnidade() {
		return codUnidade;
	}
	
	public int getCodAtendente() {
		return codAtendente;
	}
	
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
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}
	/**
	 * @return the dataHoraComeco
	 */
	public Date getDataHoraComeco() {
		return dataHoraComeco;
	}
	/**
	 * @return the dataHoraFim
	 */
	public Date getDataHoraFim() {
		return dataHoraFim;
	}
	/**
	 * @param codAgendamento the codAgendamento to set
	 */
	public void setCodAgendamento(int codAgendamento) {
		this.codAgendamento = codAgendamento;
	}
	
	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}
	
	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
	}
	
	public void setCodUnidade(int codUnidade) {
		this.codUnidade = codUnidade;
	}
	
	public void setCodAtendente(int codAtendente) {
		this.codAtendente = codAtendente;
	}
	
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
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	/**
	 * @param dataHoraComeco the dataHoraComeco to set
	 */
	public void setDataHoraComeco(Date dataHoraComeco) {
		this.dataHoraComeco = dataHoraComeco;
	}
	/**
	 * @param dataHoraFim the dataHoraFim to set
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}
	
}
