package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void deveCriarEspetaculoDiarioParaODia() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		DateTime dataInicial = new DateTime();
		DateTime dataFinal = new DateTime();
		
		List<Sessao> sessoes2 = espetaculo.criaSessoes(dataInicial.toLocalDate(), dataFinal.toLocalDate(), new LocalTime(), Periodicidade.DIARIA);
		
		assertEquals(1, sessoes2.size()); 
	}
	
	@Test
	public void deveCriarEspetaculoDiarioParaTresDias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		DateTime dataInicial = new DateTime();
		DateTime dataFinal = new DateTime();
		dataFinal = dataFinal.plusDays(3);
		
		List<Sessao> sessoes2 = espetaculo.criaSessoes(dataInicial.toLocalDate(), dataFinal.toLocalDate(), new LocalTime(), Periodicidade.DIARIA);
		
		assertEquals(3, sessoes2.size()); 
	}
	
	@Test
	public void deveCriarEspetaculoSemanalParaODia() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		DateTime dataInicial = new DateTime();
		DateTime dataFinal = new DateTime();
		
		List<Sessao> sessoes2 = espetaculo.criaSessoes(dataInicial.toLocalDate(), dataFinal.toLocalDate(), new LocalTime(), Periodicidade.SEMANAL);
		
		assertEquals(1, sessoes2.size()); 
	}
	
	@Test
	public void deveCriarEspetaculoSemanalParaDuasSemanas() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		DateTime dataInicial = new DateTime();
		DateTime dataFinal = new DateTime();
		dataFinal = dataFinal.plusWeeks(2);
		
		List<Sessao> sessoes2 = espetaculo.criaSessoes(dataInicial.toLocalDate(), dataFinal.toLocalDate(), new LocalTime(), Periodicidade.SEMANAL);
		
		assertEquals(2, sessoes2.size()); 
	}
	
	@Test
	public void deveCriarEspetaculoSemanalParaVinteSemanas() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		DateTime dataInicial = new DateTime();
		DateTime dataFinal = new DateTime();
		dataFinal = dataFinal.plusWeeks(20);
		
		List<Sessao> sessoes2 = espetaculo.criaSessoes(dataInicial.toLocalDate(), dataFinal.toLocalDate(), new LocalTime(), Periodicidade.SEMANAL);
		
		assertEquals(20, sessoes2.size()); 
	}
	
	@Test
	public void deveCriarSessoesNoIntervaloCorreto() throws Exception {
		
		Espetaculo espetaculo = new Espetaculo();
		DateTime inicio = new DateTime();
		DateTime fim = inicio.plusDays(3);
		
		Periodicidade diaria = Periodicidade.DIARIA;
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio.toLocalDate(), fim.toLocalDate(), new LocalTime(), diaria);
		
		for(Sessao sessao : sessoes) {
			Interval intervalo = new Interval(inicio, fim);
			
			if(!intervalo.contains(sessao.getInicio())){
				Assert.fail("Data Inicio fora do intervalo");
			}
		}
		
	}
	
	@Test
	public void deveCriarSessoesNosDiasCorretos() throws Exception {
		
		Espetaculo espetaculo = new Espetaculo();
		DateTime inicio = new DateTime();
		DateTime fim = inicio.plusDays(3);
		
		Periodicidade diaria = Periodicidade.DIARIA;
		LocalTime horario = new LocalTime();
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio.toLocalDate(), fim.toLocalDate(), horario, diaria);
		
		int quantidadeSessoes = diaria.quantidadeDiferenca(inicio.toLocalDate(), fim.toLocalDate());
		
		do {
			Sessao sessao = new Sessao();
			sessao.setInicio(inicio.toLocalDate().toDateTime(horario));
			System.out.println(inicio);
			Assert.assertThat(sessoes, Matchers.hasItem(sessao));
			inicio = inicio.plusDays(diaria.getDays());
			quantidadeSessoes --;
			
		} while(quantidadeSessoes > 0);
		
	}
}
