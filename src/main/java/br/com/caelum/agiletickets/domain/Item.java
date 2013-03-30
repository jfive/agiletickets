package br.com.caelum.agiletickets.domain;

public class Item {

	private final String nome;
	private final double preco;

	public Item(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public double getPreco() {
		return this.preco;
	}

}
