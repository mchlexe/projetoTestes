package br.edu.ifpb.testesUnitarios;

import org.junit.Assert;
import org.junit.Test;

public class CaixaTeste {

    @Test
    public void criarNF(){

        Caixa nf = new Caixa(101010);

        Produto produto1 = new Produto("Produto01", 20.0, 10,0.0);
        nf.addProduto(produto1,1);

        Assert.assertEquals(Integer.valueOf(101010), nf.getNumero());
        Assert.assertEquals(Integer.valueOf(1), nf.getQuantidade());
    }

    @Test
    public void fecharCompra(){

        Caixa nf = new Caixa(101010);

        Produto produto1 = new Produto("Produto01", 20.0, 10, 0.0);
        Produto produto2 = new Produto("Produto02", 30.0, 5, 0.0);
        nf.addProduto(produto1,1);
        nf.addProduto(produto2,1);
        nf.fecharCompra();

        Assert.assertEquals(Integer.valueOf(101010), nf.getNumero());
        Assert.assertEquals(Integer.valueOf(2), nf.getQuantidade());
        Assert.assertEquals(Double.valueOf(50L), nf.getValorTotal());
    }


    @Test
    public void aplicaDesconto(){

        Caixa c = new Caixa(101010);

        Produto produto1 = new Produto("Produto01", 20.0, 10, 0.1);
        c.addProduto(produto1,1);
        c.fecharCompra();

        Assert.assertEquals(Double.valueOf(18), c.getValorTotal());



    }
}
