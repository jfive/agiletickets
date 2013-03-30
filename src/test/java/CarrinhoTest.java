import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.agiletickets.domain.Carrinho;
import br.com.caelum.agiletickets.domain.Item;



public class CarrinhoTest {

	@Test
	public void testaComUmProduto() throws Exception {
		
		Carrinho c = new Carrinho(new Item("Bola", 10.0));
		
		double preco = c.getMaiorPreco();
		
		Assert.assertEquals(10.0,preco,0.0001);
		
	}
	
	@Test
	public void testaComDoisProduto() throws Exception {
		
		Carrinho c = new Carrinho(new Item("Bola", 10.0));
		c.adicionaItem(new Item("Quadrado",15.0));
		
		double preco = c.getMaiorPreco();
		
		Assert.assertEquals(15.0,preco,0.0001);
		
	}
	
	
}
