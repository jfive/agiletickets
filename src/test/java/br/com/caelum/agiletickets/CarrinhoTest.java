package br.com.caelum.agiletickets;

import br.com.caelum.agiletickets.domain.Carrinho;
import br.com.caelum.agiletickets.domain.Item;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigoalmeida
 * Date: 06/04/13
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public class CarrinhoTest {

    @Test
    public void comUmProduto() throws Exception {

        Carrinho c = new Carrinho(new Item("Bola", 10.0));

        double preco = c.getMaiorPreco();

        Assert.assertEquals(10.0, preco, 0.0001);

    }

    @Test
    public void comDoisProduto() throws Exception {

        Carrinho c = new Carrinho(new Item("Bola", 10.0));
        c.adicionaItem(new Item("Quadrado",15.0));

        double preco = c.getMaiorPreco();

        Assert.assertEquals(15.0,preco,0.0001);

    }


}

