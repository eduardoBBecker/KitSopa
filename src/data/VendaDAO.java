package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import connection.Conexao;

public class VendaDAO {

    private Conexao conexao;
    private Connection conn;

    public VendaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void salvarVenda(Venda venda, List<ProdutoVendido> itensVendidos) {
        String sql = "INSERT INTO venda (cliente_id, data_venda, data_entrega, valor_total) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getCliente().getId());
            stmt.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));
            stmt.setDate(3, new java.sql.Date(venda.getDataEntrega().getTime()));
            stmt.setDouble(4, venda.getValorTotal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int vendaId = obterUltimoIdInserido();

        // Salvar os itens vendidos no banco de dados
        for (ProdutoVendido produtoVendido : itensVendidos) {
            salvarItemVendido(produtoVendido, vendaId);
        }

        JOptionPane.showMessageDialog(null, "Venda salva com sucesso.", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private int obterUltimoIdInserido() {
        String sql = "SELECT last_insert_id()";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private void salvarItemVendido(ProdutoVendido produtoVendido, int vendaId) {
        String sql = "INSERT INTO item_vendido (venda_id, produto_id, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vendaId);
            stmt.setInt(2, produtoVendido.getProduto().getId());
            stmt.setInt(3, produtoVendido.getQuantidade());
            stmt.setDouble(4, produtoVendido.getProduto().getValorUnitario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
