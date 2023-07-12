package data;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoDAO {

    private Conexao conexao;
    private Connection conn;

    public PedidoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public List<Pedido> listarPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String sql = """
                 SELECT V.data_venda, C.nome, E.logradouro, E.numero, E.bairro, E.cidade, COUNT(DISTINCT IV.produto_id) AS QTDE_ITENS, V.valor_total
                 FROM venda V
                 JOIN cliente C
                 ON C.id = V.cliente_id
                 JOIN endereco E
                 ON E.id = C.endereco_id
                 JOIN item_vendido IV
                 ON V.id = IV.venda_id
                 GROUP BY V.data_venda, C.nome, E.logradouro, E.numero, E.bairro, E.cidade, V.valor_total
                 ORDER BY V.data_venda;""";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date dataVenda = rs.getDate("data_venda");
                String nomeCliente = rs.getString("nome");
                String endereco = rs.getString("logradouro") + ", " + rs.getString("numero") + ", " + rs.getString("bairro") + " - " + rs.getString("cidade");
                int quantidadeItens = rs.getInt("QTDE_ITENS");
                double valorTotal = rs.getDouble("valor_total");

                Pedido pedido = new Pedido(dataVenda, nomeCliente, endereco, quantidadeItens, valorTotal);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public List<Pedido> listarPedidos(String dataFiltro) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String sql = """
             SELECT V.data_venda, C.nome, E.logradouro, E.numero, E.bairro, E.cidade, COUNT(DISTINCT IV.produto_id) AS QTDE_ITENS, V.valor_total
             FROM venda V
             JOIN cliente C
             ON C.id = V.cliente_id
             JOIN endereco E
             ON E.id = C.endereco_id
             JOIN item_vendido IV
             ON V.id = IV.venda_id
             WHERE V.data_venda = ?
             GROUP BY V.data_venda, C.nome, E.logradouro, E.numero, E.bairro, E.cidade, V.valor_total
             ORDER BY V.data_venda;""";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dataFiltro);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date dataVenda = rs.getDate("data_venda");
                String nomeCliente = rs.getString("nome");
                String endereco = rs.getString("logradouro") + ", " + rs.getString("numero") + ", " + rs.getString("bairro") + " - " + rs.getString("cidade");
                int quantidadeItens = rs.getInt("QTDE_ITENS");
                double valorTotal = rs.getDouble("valor_total");

                Pedido pedido = new Pedido(dataVenda, nomeCliente, endereco, quantidadeItens, valorTotal);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public List<Pedido> listarPedidosPorCliente(String cliente) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String sql = """
             SELECT V.data_venda, C.nome, E.logradouro, E.numero, E.bairro, E.cidade, COUNT(DISTINCT IV.produto_id) AS QTDE_ITENS, V.valor_total
             FROM venda V
             JOIN cliente C
             ON C.id = V.cliente_id
             JOIN endereco E
             ON E.id = C.endereco_id
             JOIN item_vendido IV
             ON V.id = IV.venda_id
             WHERE C.nome = ?
             GROUP BY V.data_venda, C.nome, E.logradouro, E.numero, E.bairro, E.cidade, V.valor_total
             ORDER BY V.data_venda;""";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date dataVenda = rs.getDate("data_venda");
                String nomeCliente = rs.getString("nome");
                String endereco = rs.getString("logradouro") + ", " + rs.getString("numero") + ", " + rs.getString("bairro") + " - " + rs.getString("cidade");
                int quantidadeItens = rs.getInt("QTDE_ITENS");
                double valorTotal = rs.getDouble("valor_total");

                Pedido pedido = new Pedido(dataVenda, nomeCliente, endereco, quantidadeItens, valorTotal);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

}
