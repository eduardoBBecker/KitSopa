package data;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private Conexao conexao;
    private Connection conn;

    public UsuarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public Usuario validarLogin(String nome, String senha) {
        Usuario user = null;
        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                user.setId_permissao(rs.getInt("permissao_id"));
                // Preencha os demais atributos do usuário, se necessário
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
