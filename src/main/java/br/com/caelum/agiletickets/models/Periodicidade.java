package br.com.caelum.agiletickets.models;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;

public enum Periodicidade {
	
	DIARIA(1) {
		
		@Override
		public int quantidadeDiferenca(LocalDate inicio, LocalDate fim) {
			return Days.daysBetween(inicio, fim).getDays();
		}
		
	}, SEMANAL(7) {
		
		@Override
		public int quantidadeDiferenca(LocalDate inicio, LocalDate fim) {
			return Weeks.weeksBetween(inicio, fim).getWeeks();
		}
		
	};
	
	private int days;
	
	
	private Periodicidade(int days) {
		this.days = days;
	}

	public int getDays() {
		return days;
	}

	public abstract int quantidadeDiferenca(LocalDate inicio, LocalDate fim);
}
