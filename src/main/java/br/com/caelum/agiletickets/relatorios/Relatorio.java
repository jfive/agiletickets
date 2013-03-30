package br.com.caelum.agiletickets.relatorios;

public abstract class Relatorio {

	protected String cabecalho() {
		return "Cabecalho";
	}
	
	
	protected String rodape() {
		return "Rodape";
	}
	
	public abstract String corpo();
	
	public final String imprimir() {
		StringBuffer sb = new StringBuffer();
		sb.append(cabecalho());
		sb.append(corpo());
		sb.append(rodape());
		return sb.toString();
	}
	
}
