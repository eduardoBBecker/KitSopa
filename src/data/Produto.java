package data;

public class Produto {

    private int id;
    private String nome;
    private double valorUnitario;
    private String descricao;

    public Produto(int id, String nome, double valorUnitario, String descricao) {
        this.id = id;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.descricao = descricao;
    }
    
    public Produto(){
        
    }

    public Produto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
