package data;

import java.util.Date;

public class Pedido {
    private Date dataVenda;
    private String nomeCliente;
    private String endereco;
    private int quantidadeItens;
    private double valorTotal;

    public Pedido(Date dataVenda, String nomeCliente, String endereco, int quantidadeItens, double valorTotal) {
        this.dataVenda = dataVenda;
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.quantidadeItens = quantidadeItens;
        this.valorTotal = valorTotal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }


    
}