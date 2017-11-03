package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.FabricaConexao;
import to.TOMedico;

public class DAOMedico {
	
	public void cadastrarMedico(TOMedico toMedico, int codLoginCadastrado){
		String sqlInsert = "INSERT INTO tcc.medico(numeroEndereco,cRM,cRO,nomeMedico,cPF,dataNascimento,email,estadoCivil,nacionalidade,endereco,cEP,cidade,uF,pais,tel1,tel2,cel,dataCadastro,codLogin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,current_timestamp(),?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {

			java.util.Date dataUtil = new java.util.Date();

			Date dataSql = new Date(dataUtil.getTime());
		
			stm.setString(1,toMedico.getNumeroEndereco());
			stm.setString(2,toMedico.getCrm());
			stm.setString(3,toMedico.getCro());
			stm.setString(4,toMedico.getNome());
			stm.setString(5,toMedico.getCpf());
			stm.setDate(6,(Date) toMedico.getDataNascimento() );
			stm.setString(7,toMedico.getEmail()) ;
			stm.setString(8,toMedico.getEstadoCivil()) ;
			stm.setString(9,toMedico.getNacionalidade());
			stm.setString(10,toMedico.getEndereco());
			stm.setString(11,toMedico.getCep()) ;
			stm.setString(12,toMedico.getCidade() );
			stm.setString(13,toMedico.getUf()) ;
			stm.setString(14,toMedico.getPais() );
			stm.setString(15,toMedico.getTel1()); 
			stm.setString(16,toMedico.getTel2()); 
			stm.setString(17,toMedico.getCel()) ;
			stm.setInt(18,codLoginCadastrado);

			stm.execute();
			
			String sqlSelect = "SELECT LAST_INSERT_ID()";
			
			try(PreparedStatement stm1 = conn.prepareStatement(sqlSelect);
					ResultSet rs = stm1.executeQuery();){
					if(rs.next()){
						toMedico.setCodMedico(rs.getInt(1));
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void alterarMedico(TOMedico toMedico){
		String sqlUpdate = "UPDATE tcc.medico SET numeroEndereco = ?, crm = ?, cro = ?, nomeMedico = ?, cPF = ?, dataNascimento = ?, email = ?, estadoCivil = ?, nacionalidade = ?, endereco = ?, cEP = ?, cidade = ?, uF = ?, pais = ?, tel1 = ?, tel2 = ?, cel = ? WHERE codMedico = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {		
			
			stm.setString(1,toMedico.getNumeroEndereco());
			stm.setString(2,toMedico.getCrm());
			stm.setString(3,toMedico.getCro());
			stm.setString(4,toMedico.getNome());
			stm.setString(5,toMedico.getCpf());
			stm.setDate(6,(Date) toMedico.getDataNascimento() );
			stm.setString(7,toMedico.getEmail()) ;
			stm.setString(8,toMedico.getEstadoCivil()) ;
			stm.setString(9,toMedico.getNacionalidade());
			stm.setString(10,toMedico.getEndereco());
			stm.setString(11,toMedico.getCep()) ;
			stm.setString(12,toMedico.getCidade() );
			stm.setString(13,toMedico.getUf()) ;
			stm.setString(14,toMedico.getPais() );
			stm.setString(15,toMedico.getTel1()); 
			stm.setString(16,toMedico.getTel2()); 
			stm.setString(17,toMedico.getCel()) ;
			stm.setInt(18,toMedico.getCodMedico());
			
			
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluirMedico(TOMedico toMedico){
		String sqlDelete = "UPDATE tcc.medico SET flagAtivo = 0 WHERE codMedico = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			
			stm.setInt(1, toMedico.getCodMedico());
			
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TOMedico consultarMedicoCod(int codMedicoBusca){
		TOMedico toMedico = new TOMedico();
		toMedico.setCodMedico(codMedicoBusca);
		String sqlSelect = "SELECT * FROM tcc.medico where codMedico = ? and flagAtivo = 1";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, codMedicoBusca);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					
					toMedico.setNumeroEndereco(rs.getString("numeroEndereco"));
					toMedico.setDataCadastro(rs.getDate("dataCadastro"));
					toMedico.setNome(rs.getString("nomeMedico"));
					toMedico.setCpf(rs.getString("cPF"));
					toMedico.setDataNascimento(rs.getDate("dataNascimento"));
					toMedico.setEstadoCivil(rs.getString("estadoCivil"));
					toMedico.setNacionalidade(rs.getString("nacionalidade"));
					toMedico.setEndereco(rs.getString("endereco"));
					toMedico.setCep(rs.getString("cEP"));
					toMedico.setCidade(rs.getString("cidade"));
					toMedico.setUf(rs.getString("uF"));
					toMedico.setPais(rs.getString("pais"));
					toMedico.setTel1(rs.getString("tel1"));
					toMedico.setTel2(rs.getString("tel2"));
					toMedico.setCel(rs.getString("cel"));
					toMedico.setFlagAtivo(rs.getString("flagAtivo"));
					toMedico.setCrm(rs.getString("cRM"));
					toMedico.setCro(rs.getString("cRO"));
					toMedico.setEmail(rs.getString("email"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return toMedico;
	}
	
	public ArrayList<TOMedico> listarMedicos(){
		TOMedico toMedico;
		ArrayList<TOMedico> lista = new ArrayList<>();
		String sqlSelect = "SELECT * FROM tcc.medico where flagAtivo = 1 order by codMedico desc";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					toMedico = new TOMedico();
								
					toMedico.setCodMedico(rs.getInt("codMedico"));
					toMedico.setNumeroEndereco(rs.getString("numeroEndereco"));
					toMedico.setDataCadastro(rs.getDate("dataCadastro"));
					toMedico.setNome(rs.getString("nomeMedico"));
					toMedico.setCpf(rs.getString("cPF"));
					toMedico.setDataNascimento(rs.getDate("dataNascimento"));
					toMedico.setEstadoCivil(rs.getString("estadoCivil"));
					toMedico.setNacionalidade(rs.getString("nacionalidade"));
					toMedico.setEndereco(rs.getString("endereco"));
					toMedico.setCep(rs.getString("cEP"));
					toMedico.setCidade(rs.getString("cidade"));
					toMedico.setUf(rs.getString("uF"));
					toMedico.setPais(rs.getString("pais"));
					toMedico.setTel1(rs.getString("tel1"));
					toMedico.setTel2(rs.getString("tel2"));
					toMedico.setCel(rs.getString("cel"));
					toMedico.setFlagAtivo(rs.getString("flagAtivo"));
					toMedico.setCrm(rs.getString("cRM"));
					toMedico.setCro(rs.getString("cRO"));
					toMedico.setEmail(rs.getString("email"));
									
					lista.add(toMedico);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	
	
	public ArrayList<TOMedico> listarMedicosCod(String codMedico){
		TOMedico toMedico;
		ArrayList<TOMedico> lista = new ArrayList<>();
							
		String sqlSelect = "SELECT * FROM tcc.medico where codMedico = ? and and flagAtivo = 1";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, codMedico);
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					toMedico = new TOMedico();
					
					toMedico.setCodMedico(rs.getInt("codMedico"));
					toMedico.setNumeroEndereco(rs.getString("numeroEndereco"));
					toMedico.setDataCadastro(rs.getDate("dataCadastro"));
					toMedico.setNome(rs.getString("nomeMedico"));
					toMedico.setCpf(rs.getString("cPF"));
					toMedico.setDataNascimento(rs.getDate("dataNascimento"));
					toMedico.setEstadoCivil(rs.getString("estadoCivil"));
					toMedico.setNacionalidade(rs.getString("nacionalidade"));
					toMedico.setEndereco(rs.getString("endereco"));
					toMedico.setCep(rs.getString("cEP"));
					toMedico.setCidade(rs.getString("cidade"));
					toMedico.setUf(rs.getString("uF"));
					toMedico.setPais(rs.getString("pais"));
					toMedico.setTel1(rs.getString("tel1"));
					toMedico.setTel2(rs.getString("tel2"));
					toMedico.setCel(rs.getString("cel"));
					toMedico.setFlagAtivo(rs.getString("flagAtivo"));
					toMedico.setCrm(rs.getString("cRM"));
					toMedico.setCro(rs.getString("cRO"));
					toMedico.setEmail(rs.getString("email"));
									
					lista.add(toMedico);
				}
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}	
	
	
	
	
	public ArrayList<TOMedico> listarMedico(String chave){
		TOMedico toMedico;
		ArrayList<TOMedico> lista = new ArrayList<>();
							
		String sqlSelect = "SELECT * from tcc.medico where upper(nomeMedico) like ? and flagAtivo = 1";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					toMedico = new TOMedico();
					
					toMedico.setCodMedico(rs.getInt("codMedico"));
					toMedico.setNumeroEndereco(rs.getString("numeroEndereco"));
					toMedico.setDataCadastro(rs.getDate("dataCadastro"));
					toMedico.setNome(rs.getString("nomeMedico"));
					toMedico.setCpf(rs.getString("cPF"));
					toMedico.setDataNascimento(rs.getDate("dataNascimento"));
					toMedico.setEstadoCivil(rs.getString("estadoCivil"));
					toMedico.setNacionalidade(rs.getString("nacionalidade"));
					toMedico.setEndereco(rs.getString("endereco"));
					toMedico.setCep(rs.getString("cEP"));
					toMedico.setCidade(rs.getString("cidade"));
					toMedico.setUf(rs.getString("uF"));
					toMedico.setPais(rs.getString("pais"));
					toMedico.setTel1(rs.getString("tel1"));
					toMedico.setTel2(rs.getString("tel2"));
					toMedico.setCel(rs.getString("cel"));
					toMedico.setFlagAtivo(rs.getString("flagAtivo"));
					toMedico.setCrm(rs.getString("cRM"));
					toMedico.setCro(rs.getString("cRO"));
					toMedico.setEmail(rs.getString("email"));
									
					lista.add(toMedico);
				}
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}	
	
	
	public ArrayList<TOMedico> listarMedicoEspecialidadeUnidade(int unidadeValor, String especialidadeValor){
		TOMedico toMedico;
		ArrayList<TOMedico> lista = new ArrayList<>();
							
		String sqlSelect = "SELECT M.* FROM tcc.Medico M INNER JOIN tcc.Unidade U ON M.codUnidade = U.codUnidade INNER JOIN tcc.AssociativaMedicoEspecialidade AME ON M.codMedico = AME.codMedico INNER JOIN tcc.Especialidade E ON AME.codEspecialidade = E.codEspecialidade WHERE U.codUnidade = ? AND E.especialidade = ? and m.flagAtivo = 1";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, unidadeValor);
				stm.setString(2, especialidadeValor);
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					toMedico = new TOMedico();
					
					toMedico.setCodMedico(rs.getInt("codMedico"));
					toMedico.setNumeroEndereco(rs.getString("numeroEndereco"));
					toMedico.setDataCadastro(rs.getDate("dataCadastro"));
					toMedico.setNome(rs.getString("nomeMedico"));
					toMedico.setCpf(rs.getString("cPF"));
					toMedico.setDataNascimento(rs.getDate("dataNascimento"));
					toMedico.setEstadoCivil(rs.getString("estadoCivil"));
					toMedico.setNacionalidade(rs.getString("nacionalidade"));
					toMedico.setEndereco(rs.getString("endereco"));
					toMedico.setCep(rs.getString("cEP"));
					toMedico.setCidade(rs.getString("cidade"));
					toMedico.setUf(rs.getString("uF"));
					toMedico.setPais(rs.getString("pais"));
					toMedico.setTel1(rs.getString("tel1"));
					toMedico.setTel2(rs.getString("tel2"));
					toMedico.setCel(rs.getString("cel"));
					toMedico.setFlagAtivo(rs.getString("flagAtivo"));
					toMedico.setCrm(rs.getString("cRM"));
					toMedico.setCro(rs.getString("cRO"));
					toMedico.setEmail(rs.getString("email"));
									
					
					lista.add(toMedico);
				}
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}	
	
	
	public ArrayList<TOMedico> listarMedicoLogado(String codLogin){
		TOMedico toMedico;
		ArrayList<TOMedico> lista = new ArrayList<>();
							
		String sqlSelect = "SELECT * FROM TCC.Medico Where codLogin = ? and flagAtivo = 1";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = FabricaConexao.getConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, codLogin);
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					toMedico = new TOMedico();
					
					toMedico.setCodMedico(rs.getInt("codMedico"));
					toMedico.setNumeroEndereco(rs.getString("numeroEndereco"));
					toMedico.setDataCadastro(rs.getDate("dataCadastro"));
					toMedico.setNome(rs.getString("nomeMedico"));
					toMedico.setCpf(rs.getString("cPF"));
					toMedico.setDataNascimento(rs.getDate("dataNascimento"));
					toMedico.setEstadoCivil(rs.getString("estadoCivil"));
					toMedico.setNacionalidade(rs.getString("nacionalidade"));
					toMedico.setEndereco(rs.getString("endereco"));
					toMedico.setCep(rs.getString("cEP"));
					toMedico.setCidade(rs.getString("cidade"));
					toMedico.setUf(rs.getString("uF"));
					toMedico.setPais(rs.getString("pais"));
					toMedico.setTel1(rs.getString("tel1"));
					toMedico.setTel2(rs.getString("tel2"));
					toMedico.setCel(rs.getString("cel"));
					toMedico.setFlagAtivo(rs.getString("flagAtivo"));
					toMedico.setCrm(rs.getString("cRM"));
					toMedico.setCro(rs.getString("cRO"));
					toMedico.setEmail(rs.getString("email"));
									
					
					lista.add(toMedico);
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
