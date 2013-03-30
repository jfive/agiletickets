package br.com.caelum.agiletickets.controllers;

import static br.com.caelum.vraptor.view.Results.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import br.com.caelum.agiletickets.domain.Agenda;
import br.com.caelum.agiletickets.domain.DiretorioDeEstabelecimentos;
import br.com.caelum.agiletickets.domain.Promocao;
import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.agiletickets.models.Periodicidade;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.google.common.base.Strings;

@Resource
public class EspetaculosController {

	private final Agenda agenda;
	private final Validator validator;
	private final Result result;
	private Estabelecimento estabelecimento;

	private final DiretorioDeEstabelecimentos estabelecimentos;

	public EspetaculosController(Agenda agenda,
			DiretorioDeEstabelecimentos estabelecimentos, Validator validator,
			Result result) {
		this.agenda = agenda;
		this.estabelecimentos = estabelecimentos;
		this.validator = validator;
		this.result = result;
	}

	@Get
	@Path("/espetaculos")
	public List<Espetaculo> lista() {
		result.include("estabelecimentos", estabelecimentos.todos());
		return agenda.espetaculos();
	}

	@Post
	@Path("/espetaculos")
	public void adiciona(Espetaculo espetaculo) {
		
		validaEspetaculo(espetaculo);

		agenda.cadastra(espetaculo);
		result.redirectTo(this).lista();
	}

	private void validaEspetaculo(Espetaculo espetaculo) {
		// aqui eh onde fazemos as varias validacoes
		// se nao tiver nome, avisa o usuario
		// se nao tiver descricao, avisa o usuario
		if (Strings.isNullOrEmpty(espetaculo.getNome())) {
			validator.add(new ValidationMessage(
					"Nome do espetaculo nao pode estar em branco", ""));
		}
		if (Strings.isNullOrEmpty(espetaculo.getDescricao())) {
			validator.add(new ValidationMessage(
					"Descricao do espetaculo nao pode estar em branco", ""));
		}
		validator.onErrorRedirectTo(this).lista();
	}

	@Get
	@Path("/sessao/{id}")
	public void sessao(Long id) {
		Sessao sessao = getSessao(id);

		result.include("sessao", sessao);
	}

	public Sessao getSessao(Long id){
		Sessao sessao = agenda.sessao(id);
		validarSessao(sessao);
		return sessao;
	}
	
	@Post @Path("/sessao/{sessaoId}/reserva")
	public void reserva(Long sessaoId, final Integer quantidade) {
		Sessao sessao = agenda.sessao(sessaoId);
		
		validarReserva(quantidade, sessao);

		sessao.reserva(quantidade);
		
		result.include("message", "Sessao reservada com sucesso");

		result.redirectTo(IndexController.class).index();
	}

	private void validarReserva(final Integer quantidade, Sessao sessao) {
		
		validarSessao(sessao);

		if (quantidade < 1) {
			validator.add(new ValidationMessage(
					"Voce deve escolher um lugar ou mais", ""));
		}

		if (!sessao.podeReservar(quantidade)) {
			validator.add(new ValidationMessage(
					"Nao existem ingressos disponiveis", ""));
		}

		// em caso de erro, redireciona para a lista de sessao
		validator.onErrorRedirectTo(this).sessao(sessao.getId());
	}

	private void validarSessao(Sessao sessao) {
		if (sessao == null) {
			result.notFound();
			return;
		}
	}

	@Get
	@Path("/espetaculo/{espetaculoId}/sessoes")
	public void sessoes(Long espetaculoId) {
		Espetaculo espetaculo = carregaEspetaculo(espetaculoId);

		result.include("espetaculo", espetaculo);
	}

	@Post
	@Path("/espetaculo/{espetaculoId}/sessoes")
	public void cadastraSessoes(Long espetaculoId, LocalDate inicio,
			LocalDate fim, LocalTime horario, Periodicidade periodicidade) {
		Espetaculo espetaculo = carregaEspetaculo(espetaculoId);

		criarSessaoPorPeriodo(inicio, fim, horario, periodicidade, espetaculo);
		
		result.redirectTo(this).lista();
	}

	private void criarSessaoPorPeriodo(LocalDate inicio, LocalDate fim,
			LocalTime horario, Periodicidade periodicidade,
			Espetaculo espetaculo) {
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);

		agenda.agende(sessoes);
		
		result.include("message", sessoes.size() + " sessoes criadas com sucesso");

	}

	@Get
	@Path("/asdsdfg")
	public void promocoesDisponiveisParaEspetaculo() {

		List<Promocao> todasPromocoes = new ArrayList<Promocao>();
		Espetaculo espetaculo = new Espetaculo();

		Map<Promocao, List<Sessao>> sessoesPromocionais = new HashMap<Promocao, List<Sessao>>();

		for (Promocao promocao : todasPromocoes) {
			for (Sessao s : espetaculo.getSessoes()) {
				if (promocao.isSempre()	|| s.getIngressosDisponiveis() <= s.getTotalIngressos() * 0.1) {
					if (s.dentroDoIntervalo(promocao)) {
						if (sessoesPromocionais.containsKey(promocao)) {
							sessoesPromocionais.get(promocao).add(s);
						} else {
							sessoesPromocionais.put(promocao,
									new ArrayList<Sessao>(Arrays.asList(s)));
						}
					}
				}
			}
		}
	}

	private Espetaculo carregaEspetaculo(Long espetaculoId) {
		Espetaculo espetaculo = agenda.espetaculo(espetaculoId);
		if (espetaculo == null) {
			validator.add(new ValidationMessage("", ""));
		}
		validator.onErrorUse(status()).notFound();
		return espetaculo;
	}

}
