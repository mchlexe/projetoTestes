package br.edu.ifpb.testesUnitarios;

public class Produto {
    private String nome;
    private Double valor;
    private Integer estoque;
    private Double desconto;

    public Produto(String nome, Double valor, Integer estoque, Double desconto) {

        if (valor < 0 || estoque < 0 || desconto > 1.0 || desconto < 0.0) {
            throw new IllegalArgumentException("Valores inválidos.");
        } else {
            this.nome = nome;
            this.valor = valor;
            this.estoque = estoque;
            this.desconto = desconto;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        if ((desconto > 1) || (desconto < 0)) {
            throw new IllegalArgumentException("O desconto deve ser um decimal entre 0 e 1.");
        } else {
            this.desconto = desconto;
        }
    }
}
