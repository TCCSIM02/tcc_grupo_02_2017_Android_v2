package dao;

/**
 * Created by Nilton on 07/10/2017.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


            stm.setInt(1,1);
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
}

