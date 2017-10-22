package connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao  {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Obtem conexao com o banco de dados
    // Alterar para O TCC
    public static Connection getConexao() throws SQLException {
        //return DriverManager.getConnection("jdbc:mysql://127.0.0.1/TCC?user=alunos&password=alunos");
        return DriverManager.getConnection("jdbc:mysql://tccdbawsintance.capgtveukqse.sa-east-1.rds.amazonaws.com:3306/TCC?user=alunos&password=alunos_tcc");

        /*	Database name: tcc
		 * 	Database port: 3306
		 * 	Endpoint: tccdbawsintance.capgtveukqse.sa-east-1.rds.amazonaws.com
		 * */
    }
}

