package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    public Connection getConexao() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ kit_sopa", // linha de conexao
                    "Admin", // usuario do mysql
                    "Rps@32admin"// senha do mysql
            );
            return conn;

        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}
