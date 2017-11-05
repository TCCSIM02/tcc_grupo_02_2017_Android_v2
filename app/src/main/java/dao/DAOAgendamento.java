package dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import connectionFactory.FabricaConexao;
import to.TOAgendamento;

public class DAOAgendamento {
	
	public void cadastrarAgendamento(TOAgendamento toAgendamento){
		String sqlInsert = "insert into tcc.agendamento (codPaciente, codMedico, codUnidade, codEspecialidade, dataAgendamentoComeco, dataAgendamentoFim, flagAtivo, dataCadastro) \n" +
				"select codPaciente, ?, ?,  ? , ?, date_add(?,interval 1 HOUR), 1 , current_timestamp() from tcc.paciente where codLogin = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		Log.e("dataHoraComeco","1");
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {

			Log.e("dataHoraComeco","2");
			//java.sql.Date dataSql = new java.sql.Date(toAgendamento.getDataHoraComeco().getTime());
			java.sql.Timestamp dataSql = new java.sql.Timestamp(toAgendamento.getDataHoraComeco().getTime());
			Log.e("dataHoraComeco","3 " + dataSql);

			stm.setInt(1,toAgendamento.getCodMedico());
			Log.e("dataHoraComeco","4 " + toAgendamento.getCodMedico());
			stm.setInt(2,toAgendamento.getCodUnidade());
			Log.e("dataHoraComeco","5 " + toAgendamento.getCodUnidade());
			stm.setInt(3,toAgendamento.getCodEspecialidade());
			Log.e("dataHoraComeco","6 " + toAgendamento.getCodEspecialidade());
			stm.setTimestamp(4,dataSql);
			Log.e("dataHoraComeco","7 " + dataSql);
			stm.setTimestamp(5,dataSql);
			Log.e("dataHoraComeco","8 " + dataSql);
			stm.setInt(6,toAgendamento.getCodLogin());
			Log.e("dataHoraComeco","9 " + toAgendamento.getCodLogin());
			stm.execute();
			Log.e("dataHoraComeco","10");

		} catch (SQLException e) {
			Log.e("dataHoraComeco","11 CATCH");
			e.printStackTrace();
		}
		Log.e("dataHoraComeco","12");
	}
	
	public void alterarAgendamento(TOAgendamento toAgendamento){
		String sqlUpdate = "UPDATE tcc.agendamento SET dataAgendamentoComeco = ?, dataAgendamentoFim = ?, statusAgendamento = ?, flagAtivo = ?, dataCadastro = ? WHERE codAgendamento = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			
			stm.setDate(1,(java.sql.Date) toAgendamento.getDataHoraComeco());
			stm.setDate(2,(java.sql.Date) toAgendamento.getDataHoraFim());
			stm.setInt(3,Integer.parseInt(toAgendamento.getFlagAtivo()));
			stm.setInt(4,toAgendamento.getCodAgendamento());
			
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluirAgendamento(TOAgendamento toAgendamento){
		String sqlDelete = "DELETE FROM tcc.agendamento WHERE codAgendamento = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			
			stm.setInt(1, toAgendamento.getCodAgendamento());
			
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TOAgendamento consultarAgendamentoCod(int codAgendamentoBusca){
		TOAgendamento toAgendamento = new TOAgendamento();
		toAgendamento.setCodAgendamento(codAgendamentoBusca);
		String sqlSelect = "SELECT  dataAgendamentoComeco, dataAgendamentoFim, statusAgendamento, flagAtivo, dataCadastro FROM tcc.agendamento where codAgendamento = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, codAgendamentoBusca);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					
					//toAgendamento.setCodAgendamento(rs.getInt("codAgendamento"));
					toAgendamento.setDataHoraComeco(rs.getDate("dataAgendamentoComeco"));
					toAgendamento.setDataHoraFim(rs.getDate("dataAgendamentoFim")); 							
					toAgendamento.setFlagAtivo(rs.getString("flagAtivo"));
					toAgendamento.setDataCadastro(rs.getDate("dataCadastro"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return toAgendamento;
	}
	
	public ArrayList<TOAgendamento> listarAgendamentos() throws ParseException{
		TOAgendamento toAgendamento;
		ArrayList<TOAgendamento> lista = new ArrayList<>();
		String sqlSelect = "SELECT  codAgendamento, CAST(dataAgendamentoComeco AS char) as dataAgendamentoComeco, CAST(dataAgendamentoFim AS char) as dataAgendamentoFim, statusAgendamento, flagAtivo, dataCadastro FROM tcc.agendamento where codAgendamento = 90 order by codAgendamento desc";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					toAgendamento = new TOAgendamento();
					
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					toAgendamento.setCodAgendamento(rs.getInt("codAgendamento"));
					toAgendamento.setDataHoraComeco(format.parse(rs.getString("dataAgendamentoComeco")));
					toAgendamento.setDataHoraFim(format.parse(rs.getString("dataAgendamentoFim"))); 						
					toAgendamento.setFlagAtivo(rs.getString("flagAtivo"));
					toAgendamento.setDataCadastro(rs.getDate("dataCadastro"));
					
					format.format(format.parse(rs.getString("dataAgendamentoComeco")));
					
					//System.out.println("chegada: " + rs.getString("dataAgendamentoComeco") + " / " + format.parse(rs.getString("dataAgendamentoComeco")) + " / " + format.format(format.parse(rs.getString("dataAgendamentoComeco"))));
					//System.out.println("sa�da: " + rs.getString("dataAgendamentoFim") + " / " + format.format(rs.getDate("dataAgendamentoFim")));
					
					lista.add(toAgendamento);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	
	public ArrayList<TOAgendamento> listarAgendamentosMedico(int chave) throws ParseException{
		
		//System.out.println(chave);
		
		TOAgendamento toAgendamento;
		ArrayList<TOAgendamento> lista = new ArrayList<>();
		String sqlSelect = "SELECT * FROM tcc.agendamento A WHERE A.codMedico = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, chave);
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					toAgendamento = new TOAgendamento();
					
					//DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					toAgendamento.setCodAgendamento(rs.getInt("codAgendamento"));
					toAgendamento.setCodPaciente(rs.getInt("codPaciente"));
					toAgendamento.setCodMedico(rs.getInt("codMedico"));
					toAgendamento.setCodUnidade(rs.getInt("codUnidade"));
					toAgendamento.setCodAtendente(rs.getInt("codAtendente"));					
					toAgendamento.setDataHoraComeco(rs.getDate("dataAgendamentoComeco"));
					toAgendamento.setDataHoraFim(rs.getDate("dataAgendamentoFim")); 					
					toAgendamento.setFlagAtivo(rs.getString("flagAtivo"));
					toAgendamento.setDataCadastro(rs.getDate("dataCadastro"));
					
					//System.out.println("acima o parametro, abaixo o codagendamento");
					
					//System.out.println(toAgendamento.getCodAgendamento());
					
					//System.out.println("testeee");
					
					
					lista.add(toAgendamento);
				}
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}

	public ArrayList<String> listarMeusAgendamentos(int chave) throws ParseException{

		//System.out.println(chave);

		ArrayList<String> lista = new ArrayList<>();
		String sqlSelect = "select \n" +
				"uni.nomeFantasia\n" +
				",med.nomeMedico\n" +
				",age.dataAgendamentoComeco\n" +
				",esp.especialidade\n" +
				",age.codAgendamento\n" +
				"from \n" +
				"tcc.paciente pac\n" +
				"inner join tcc.agendamento age on pac.codPaciente = age.codPaciente and age.flagAtivo = 1\n" +
				"inner join tcc.unidade uni on age.codUnidade = uni.codUnidade\n" +
				"inner join tcc.medico med on age.codMedico = med.codMedico\n" +
				"left join tcc.especialidade esp on age.codEspecialidade = esp.codEspecialidade\n" +
				"where pac.codLogin = ?\n" +
				"group by\n" +
				"uni.nomeFantasia\n" +
				",med.nomeMedico\n" +
				",age.dataAgendamentoComeco\n" +
				",esp.especialidade\n" +
				",age.codAgendamento\n" +
				"order by \n" +
				"age.dataAgendamentoComeco desc;";
		// usando o try with resources do Java 7, que fecha o que abriu
		Log.e("Login:","CHEGAMOS AQUI 19");
		try (Connection conn = FabricaConexao.getConexao();
			 PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, chave);
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {

					DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

					String dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rs.getDate("dataAgendamentoComeco"));

					String agendamento = "";

					if(rs.getString("especialidade") == null || rs.getString("especialidade").equals("")){
						Log.e("Login:","CHEGAMOS AQUI 20");
						agendamento =rs.getString("nomeFantasia") + "\n" +
								rs.getString("nomeMedico") + "\n" +
								dataFormatada + "\n" +
								rs.getInt("codAgendamento");
					}else{

						Log.e("Login:","CHEGAMOS AQUI 20");
						agendamento =rs.getString("nomeFantasia") + "\n" +
								rs.getString("nomeMedico") + "\n" +
								dataFormatada + "\n" +
								rs.getString("especialidade") + "\n" +
								rs.getInt("codAgendamento");
					}

					Log.e("Login:","CHEGAMOS AQUI 21");
					Log.e("Agendamentos",agendamento);
					lista.add(agendamento);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}

	public ArrayList<String> listarHorariosOcupados(int pCodMedico,String data) throws ParseException{

		//System.out.println(chave);
		Log.e("DATA", "CHEGAMOS AQUI 33");
		ArrayList<String> lista = new ArrayList<>();
		String sqlSelect = "select concat((lpad(hour(dataAgendamentoComeco),2,0)),':00')  as hora\n" +
				"from tcc.agendamento a \n" +
				"where dataAgendamentoComeco =  ?\n" +
				"and codMedico = ?\n" +
				"and flagAtivo = 1\n" +
				"\n" +
				"union\n" +
				"\n" +
				"select concat((lpad(hour(dataAgendamentoFim),2,0)),':00')  as hora\n" +
				"from tcc.agendamento a \n" +
				"where dataAgendamentoComeco = ?\n" +
				"and codMedico = ?\n" +
				"and flagAtivo = 1;";
		// usando o try with resources do Java 7, que fecha o que abriu
		Log.e("Login:","CHEGAMOS AQUI 19");
		try (Connection conn = FabricaConexao.getConexao();
			 PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(2, pCodMedico);
			stm.setInt(4, pCodMedico);
			stm.setString(1, data);
			stm.setString(3, data);
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {

					String horario = "";
					Log.e("DATA", "CHEGAMOS AQUI 34");
					horario = rs.getString("hora");

					lista.add(horario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
}
