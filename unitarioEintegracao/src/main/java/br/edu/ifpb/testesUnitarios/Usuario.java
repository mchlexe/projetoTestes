package br.edu.ifpb.testesUnitarios;

public class Usuario {

    private String nome;
    private String senha;
    private Boolean logado;


    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.logado = false;
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

    public void login(String senha) throws UsuarioException {
        if (senha == this.senha ){
            this.logado = true;
        } else {
            throw new UsuarioException("Senha incorreta!");
        }
    }

    public void logout() {
        this.logado = false;
    }

    public Boolean getLogado() {
        return this.logado;
    }

}
