package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import connection.Conexao;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Conexao conexao;
    private Connection conn;

    public ClienteDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void salvarCliente(Cliente cliente, Endereco endereco) {
        // Inicia a transação
        try {
            conn.setAutoCommit(false);

            // Salva o endereço e obtém o ID gerado
            int enderecoId = salvarEndereco(endereco);

            // Insere o cliente no banco de dados junto com o ID do endereço
            String sql = "INSERT INTO cliente (nome, telefone, endereco_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setInt(3, enderecoId);
            stmt.executeUpdate();

            // Confirma a transação
            conn.commit();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            // Desfaz a transação em caso de erro
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                JOptionPane.showMessageDialog(null, "Erro ao desfazer transação: " + rollbackEx.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Erro ao inserir cliente: " + e.getMessage());
        } finally {
            // Restaura o modo de commit automático
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao restaurar o modo de commit automático: " + e.getMessage());
            }
        }
    }

    private int salvarEndereco(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO endereco (logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, endereco.getLogradouro());
        stmt.setString(2, endereco.getNumero());
        stmt.setString(3, endereco.getComplemento());
        stmt.setString(4, endereco.getBairro());
        stmt.setString(5, endereco.getCidade());
        stmt.setString(6, endereco.getEstado());
        stmt.setString(7, endereco.getCep());
        stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        int enderecoId = -1; // Valor padrão para indicar falha na obtenção do ID
        if (generatedKeys.next()) {
            enderecoId = generatedKeys.getInt(1);
        }

        return enderecoId;
    }

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT id, nome FROM cliente ORDER BY nome";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Cliente cliente = new Cliente(id, nome);
                clientes.add(cliente);
            }
        }

        return clientes;
    }
}
