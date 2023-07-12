package data;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private int id;
    private String nome;
    private String telefone;
    private Endereco endereco;

    public Cliente(int id, String nome, String telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }
   
    public Cliente() {
        
    }

    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void cadastrarCliente() {

        List<Cliente> listaClientesCadastrados = new ArrayList<>();
        listaClientesCadastrados.add(this);
        System.out.println("Cliente cadastrado com sucesso!");
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{"
                + "nome:'" + nome + '\''
                + ", telefone:" + telefone + '\''
                + ", endereco: " + endereco
                + '}';
    }

}
