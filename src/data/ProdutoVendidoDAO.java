package data;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProdutoVendidoDAO {
    
    private Conexao conexao;
    private Connection conn;

    public ProdutoVendidoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
           
}
