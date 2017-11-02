package models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import dao.DAOAgendamento;
import to.TOAgendamento;

public class ModelAgendamento {

	private int codAgendamento, codPaciente, codMedico, codUnidade, codAtendente, codEspecialidade;
	private String flagAtivo;
	private Date dataCadastro, dataHoraComeco, dataHoraFim;
	
	public ModelAgendamento() {
		// TODO Auto-generated constructor stub
	}

	public ModelAgendamento(int codAgendamento, int codPaciente, int codMedico, int codUnidade, int codAtendente, int codEspecialidade, String flagAtivo, Date dataCadastro,
			Date dataHoraComeco, Date dataHoraFim) {
		this.codAgendamento = codAgendamento;
		this.codPaciente = codPaciente;
		this.codMedico = codMedico;
		this.codUnidade = codUnidade;
		this.codAtendente = codAtendente;
		this.codEspecialidade = codEspecialidade;
		this.flagAtivo = flagAtivo;
		this.dataCadastro = dataCadastro;
		this.dataHoraComeco = dataHoraComeco;
		this.dataHoraFim = dataHoraFim;
	}

	/**
	 * @param codAgendamento
	 */
	public ModelAgendamento(int codAgendamento) {
		this.codAgendamento = codAgendamento;
	}

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

	public TOAgendamento getTO(){
		
		TOAgendamento toAgendamento = new TOAgendamento();
		
		toAgendamento.setCodAgendamento(codAgendamento);
		toAgendamento.setCodPaciente(codPaciente);
		toAgendamento.setCodMedico(codMedico);
		toAgendamento.setCodUnidade(codUnidade);
		toAgendamento.setCodAtendente(codAtendente);
		toAgendamento.setCodEspecialidade(codEspecialidade);
		toAgendamento.setDataCadastro(dataCadastro);
		toAgendamento.setDataHoraComeco(dataCadastro);
		toAgendamento.setDataHoraFim(dataCadastro);
		toAgendamento.setFlagAtivo(flagAtivo);
		
		return toAgendamento;
		
	}
	
	public void agendarConsulta(){
		
	}
	
	public void cancelarConsulta(){
		
	}

	public void cadastrarAgendamento() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<TOAgendamento> listarAgendamentos() {
		// TODO Auto-generated method stub
		return null;
	}

	public void alterarAgendamento() {
		// TODO Auto-generated method stub
		
	}

	public void consultarAgendamentoCod() {
		// TODO Auto-generated method stub
		
	}

	public void excluirAgendamento() {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<String> meusAgendamentos(int pCodLogin) throws ParseException {

		DAOAgendamento dao = new DAOAgendamento();

		ArrayList<String> listaMeusAgendamento = dao.listarMeusAgendamentos(pCodLogin);

		return listaMeusAgendamento;
	}
}