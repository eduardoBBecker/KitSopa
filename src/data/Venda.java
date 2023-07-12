package data;

import java.util.Date;
import java.util.List;

public class Venda {

    private Cliente cliente;
    private List<ProdutoVendido> produtosVendidos;
    private Date dataVenda;
    private String observacoes;
    private Date dataEntrega;
    private String status;
    private double valorTotal;

    public Venda(Cliente cliente, List<ProdutoVendido> produtosVendidos, Date dataVenda, String observacoes, Date dataEntrega, String status, double valorTotal) {
        this.cliente = cliente;
        this.produtosVendidos = produtosVendidos;
        this.dataVenda = dataVenda;
        this.observacoes = observacoes;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.valorTotal = calcularValorTotal();
    }

    public Venda(Cliente cliente, List<ProdutoVendido> produtosVendidos, Date dataVenda, String observacoes, Date dataEntrega, String status) {
        this.cliente = cliente;
        this.produtosVendidos = produtosVendidos;
        this.dataVenda = dataVenda;
        this.observacoes = observacoes;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }

    public Venda() {

    }

    public Venda(Cliente cliente, Date dataVenda, List<ProdutoVendido> itensVendidos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProdutoVendido> getProdutosVendidos() {
        return produtosVendidos;
    }

    public void setProdutosVendidos(List<ProdutoVendido> produtosVendidos) {
        this.produtosVendidos = produtosVendidos;
        this.valorTotal = calcularValorTotal();
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public double calcularValorTotal() {
        double valorTotalVendido = 0;

        if (produtosVendidos != null) {
            for (ProdutoVendido produtoVendido : produtosVendidos) {
                valorTotalVendido += produtoVendido.getQuantidade() * produtoVendido.getProduto().getValorUnitario();
            }
        }

        return valorTotalVendido;
    }
}
