package data;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProdutoDAO {

    private Conexao conexao;
    private Connection conn;

    public ProdutoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void salvarProduto(Produto produto) {
        // Inicia a transação
        try {
            conn.setAutoCommit(false);

            // Insere o produto no banco de dados
            String sql = "INSERT INTO produto (nome, valor_unitario, descricao) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValorUnitario());
            stmt.setString(3, produto.getDescricao());
            stmt.executeUpdate();

            // Confirma a transação
            conn.commit();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            // Desfaz a transação em caso de erro
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                JOptionPane.showMessageDialog(null, "Erro ao desfazer transação: " + rollbackEx.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Erro ao inserir produto: " + e.getMessage());
        } finally {
            // Restaura o modo de commit automático
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao restaurar o modo de commit automático: " + e.getMessage());
            }
        }
    }

    public List<Produto> getProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM produto";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValorUnitario(rs.getDouble("valor_unitario"));
                produto.setDescricao(rs.getString("descricao"));
                produtos.add(produto);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar produtos: " + e.getMessage());
        }

        return produtos;
    }

    public List<Produto> listarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT id, nome FROM produto";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Produto produto = new Produto(id, nome);
                produtos.add(produto);
            }
        }

        return produtos;
    }

    public double obterValorProduto(int id) {
        double valor = 0;
        try {
            String sql = "SELECT valor_unitario FROM produto WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                valor = rs.getDouble("valor_unitario");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter valor do produto: " + e.getMessage());
        }

        return valor;
    }
    
    public Produto obterProdutoPorNome(String nome) {
    Produto produto = null;
    try {
        String sql = "SELECT * FROM produto WHERE nome = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValorUnitario(rs.getDouble("valor_unitario"));
            produto.setDescricao(rs.getString("descricao"));
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao obter produto por nome: " + e.getMessage());
    }

    return produto;
}

}
