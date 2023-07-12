package data;

public class Usuario {

    private int id_usuario;
    private String nome;
    private String senha;
    private int id_permissao;

    public Usuario(int id_usuario, String nome, String senha, int id_permissao) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.senha = senha;
        this.id_permissao = id_permissao;
    }

    public Usuario() {

    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId_permissao() {
        return id_permissao;
    }

    public void setId_permissao(int id_permissao) {
        this.id_permissao = id_permissao;
    }

}
