package br.edu.ifpb.testesUnitarios;

import org.junit.Assert;
import org.junit.Test;

public class ProdutoTeste {

    @Test
    public void criarProduto() {
        Produto produto = new Produto("Produto1", 20.0, 10, 0.0);

        Assert.assertEquals("Produto1", produto.getNome());
        Assert.assertEquals(Double.valueOf(20.0), produto.getValor());
        Assert.assertEquals(Integer.valueOf(10), produto.getEstoque());
        Assert.assertEquals(Double.valueOf(0.0), produto.getDesconto());
    }

    @Test (expected = IllegalArgumentException.class)
    public void recusaValoresNegativos() {
        Produto produto = new Produto("Produto1", -10.0, -2, 0.0);
    }

}