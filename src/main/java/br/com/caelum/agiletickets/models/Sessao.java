package br.com.caelum.agiletickets.models;

import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import br.com.caelum.agiletickets.domain.Promocao;

@Entity
public class Sessao {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Espetaculo espetaculo;

	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime inicio;

	private Integer duracaoEmMinutos;

	private Integer totalIngressos = 0;

	private Integer ingressosReservados = 0;

	public Long getId() {
		return id;
	}

	public void setEspetaculo(Espetaculo espetaculo) {
		this.espetaculo = espetaculo;
	}

	public DateTime getInicio() {
		return inicio;
	}

	public void setInicio(DateTime inicio) {
		this.inicio = inicio;
	}

	public Espetaculo getEspetaculo() {
		return espetaculo;
	}

	public Integer getDuracaoEmMinutos() {
		return duracaoEmMinutos;
	}

	public void setDuracaoEmMinutos(Integer duracaoEmMinutos) {
		this.duracaoEmMinutos = duracaoEmMinutos;
	}

	public String getDia() {
		return inicio.toString(DateTimeFormat.shortDate().withLocale(new Locale("pt", "BR")));
	}

	public String getHora() {
		return inicio.toString(DateTimeFormat.shortTime().withLocale(new Locale("pt", "BR")));
	}

	public Integer getTotalIngressos() {
		return totalIngressos;
	}

	public void setTotalIngressos(Integer totalIngressos) {
		this.totalIngressos = totalIngressos;
	}

	public Integer getIngressosReservados() {
		return ingressosReservados;
	}

	public void setIngressosReservados(Integer ingressosReservados) {
		this.ingressosReservados = ingressosReservados;
	}

	public Integer getIngressosDisponiveis() {
		// faz a conta de total de ingressos menos ingressos reservados
		return totalIngressos - ingressosReservados;
	}
	
	public void reserva(Integer numeroDeIngressos) {
		// soma quantidade na variavel ingressos reservados
		this.ingressosReservados += numeroDeIngressos;
	}

	public boolean podeReservar(Integer numeroDeIngressos) {
		int sobraram = getIngressosDisponiveis() - numeroDeIngressos;
        boolean naoTemEspaco = sobraram < 0;

        return !naoTemEspaco;
	}
	
	public boolean dentroDoIntervalo(Promocao promocao) {
		return getInicio().isAfter(promocao.getInicio())
				&& getInicio().isBefore(promocao.getFim());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((duracaoEmMinutos == null) ? 0 : duracaoEmMinutos.hashCode());
		result = prime * result
				+ ((espetaculo == null) ? 0 : espetaculo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((ingressosReservados == null) ? 0 : ingressosReservados
						.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result
				+ ((totalIngressos == null) ? 0 : totalIngressos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sessao other = (Sessao) obj;
		if (duracaoEmMinutos == null) {
			if (other.duracaoEmMinutos != null)
				return false;
		} else if (!duracaoEmMinutos.equals(other.duracaoEmMinutos))
			return false;
		if (espetaculo == null) {
			if (other.espetaculo != null)
				return false;
		} else if (!espetaculo.equals(other.espetaculo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingressosReservados == null) {
			if (other.ingressosReservados != null)
				return false;
		} else if (!ingressosReservados.equals(other.ingressosReservados))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (totalIngressos == null) {
			if (other.totalIngressos != null)
				return false;
		} else if (!totalIngressos.equals(other.totalIngressos))
			return false;
		return true;
	}
	
	
}
