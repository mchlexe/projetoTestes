package br.edu.ifpb.testesUnitarios;

import java.util.LinkedList;
import java.util.List;

public class Caixa {

    private Integer numero;
    private List<Produto> produtos = new LinkedList<Produto>();
    private Double valorTotal = 0.0;

    public Caixa(Integer numero) {
        this.numero = numero;
    }
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void fecharCompra() {
        for (Produto p : produtos) {
            if (p.getDesconto() != 0.0) {
                valorTotal += (p.getValor() * (1 - p.getDesconto()));
            } else {
                valorTotal += p.getValor();
            }
        }
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Integer getQuantidade() {
        return this.produtos.size();
    }

    public void addProduto(Produto produto, Integer quantidade){
        Integer estoque = produto.getEstoque();
        if (estoque >= quantidade) {
            produto.setEstoque(estoque -= quantidade);

            for (int i = 0; i < quantidade; i++) {
                produtos.add(produto);
            }

        } else {
            throw new IllegalArgumentException("Não há estoque suficiente");
        }
    }



}