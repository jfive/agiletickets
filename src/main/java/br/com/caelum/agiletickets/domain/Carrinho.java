package br.com.caelum.agiletickets.domain;

import java.util.List;

public class Carrinho {

	private List<Item> itens;
	private Item item;
	
	public Carrinho(Item item) {
		this.item = item;
	}

	public double getMaiorPreco() {
		return item.getPreco();
	}

	public void adicionaItem(Item item) {
		if(item.getPreco() > this.item.getPreco()){
			this.item = item;
		}
	}

}
