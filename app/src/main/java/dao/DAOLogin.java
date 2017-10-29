package dao;

/**
 * Created by Nilton on 07/10/2017.
 */

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.FabricaConexao;
import to.TOLogin;

public class DAOLogin {

    public int getUltimoCodLogin(TOLogin toLogin){

        int ultimoCodLogin = -1;

        String sqlSelect = "SELECT codLogin from tcc.login where nomeLogin = '" + toLogin.getNomeLogin() + "'";

        try (Connection conn = FabricaConexao.getConexao();) {

            try(PreparedStatement stm1 = conn.prepareStatement(sqlSelect);
                ResultSet rs = stm1.executeQuery();){

                if(rs.next()){
                    ultimoCodLogin = rs.getInt("codLogin");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoCodLogin;
    }

    public void cadastrarLogin(TOLogin toLogin){
        String sqlInsert = "INSERT INTO tcc.login (codNivel,nomeLogin,senha,flagAtivo,dataCadastro) VALUES (?,?,?,1,current_timestamp())";
        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement stm = conn.prepareStatement(sqlInsert);) {

            java.util.Date dataUtil = new java.util.Date();
            //SimpleDateFormat inputdate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            //String strDate = inputdate.format(dataUtil);

            //long longDate = Long.valueOf(strDate);

            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
            //java.sql.Date dataSql = new java.sql.Date(longDate);


            stm.setInt(1,2);
            stm.setString(2,toLogin.getNomeLogin());
            stm.setString(3,toLogin.getSenhaCriptografada());
            //stm.setString(4,toLogin.getTipoLogin());
            //stm.setString(4,toLogin.getFlagAtivo());
            //stm.setDate(5,dataSql);

            stm.execute();

            String sqlSelect = "SELECT LAST_INSERT_ID()";

            try(PreparedStatement stm1 = conn.prepareStatement(sqlSelect);
                ResultSet rs = stm1.executeQuery();){
                if(rs.next()){
                    toLogin.setCodLogin(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TOLogin buscarLogin(String pNomeLogin){
        TOLogin TOLogin = new TOLogin();
        Log.e("Login:","CHEGAMOS AQUI 4,5");
        String sqlSelect = "SELECT codLogin, nomeLogin, senha, flagAtivo, dataCadastro FROM tcc.login where nomeLogin = ? and codNivel = 2";
        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setString(1, pNomeLogin);
            try (ResultSet rs = stm.executeQuery();) {
                if (rs.next()) {
                    Log.e("Login:","CHEGAMOS AQUI 5");
                    TOLogin.setCodLogin(rs.getInt("codLogin"));
                    TOLogin.setNomeLogin(rs.getString("nomeLogin"));
                    TOLogin.setSenhaCriptografada(rs.getString("senha"));
                    TOLogin.setFlagAtivo(rs.getString("flagAtivo"));
                    TOLogin.setDataCadastro(rs.getDate("dataCadastro"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Log.e("Login:","CHEGAMOS AQUI 6");
            }
        } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
            Log.e("Login:","CHEGAMOS AQUI 7");
        }
        Log.e("Login:","CHEGAMOS AQUI 8");
        return TOLogin;
    }

    public ArrayList<TOLogin> listarLogins(String chave){
        TOLogin toLogin;
        ArrayList<TOLogin> lista = new ArrayList<>();
        String sqlSelect = "select  codLogin, nomeLogin, senha, flagAtivo, dataCadastro from tcc.login where nomelogin = ? limit 1";
        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setString(1, chave);
            try (ResultSet rs = stm.executeQuery();) {
                while(rs.next()) {
                    toLogin = new TOLogin();
                    Log.e("Login:","CHEGAMOS AQUI 10");
                    toLogin.setCodLogin(rs.getInt("codLogin"));
                    toLogin.setNomeLogin(rs.getString("nomeLogin"));
                    toLogin.setSenhaCriptografada(rs.getString("senha"));
                    toLogin.setFlagAtivo(rs.getString("flagAtivo"));
                    toLogin.setDataCadastro(rs.getDate("dataCadastro"));

                    lista.add(toLogin);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Log.e("Login:","CHEGAMOS AQUI 11");
            }
        } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
        }
        return lista;
    }

}

