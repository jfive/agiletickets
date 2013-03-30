package br.com.caelum.agiletickets.relatorios;

import org.junit.Test;

public class RelatorioTest {

	@Test
	public void imprimeRelatorioRH() {
		System.out.println(new RelatorioRH().imprimir());
	}
	
	public void imprimeRelatorioFI() {
		System.out.println(new RelatorioFI().imprimir());
	}

}
