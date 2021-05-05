package fr.eni.tpsuividesrepas.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;


public class Repas {
	
	private int identifiant;
	private LocalDate date;
	private LocalTime heure;
	private String[] aliments;
	
	public Repas(int identifiant, LocalDate date, LocalTime heure, String[] aliments) {
		super();
		this.identifiant = identifiant;
		this.date = date;
		this.heure = heure;
		this.aliments = aliments;
	}

	public Repas(LocalDate date, LocalTime heure,String[] aliments) {
		super();
		this.date = date;
		this.heure = heure;
		this.aliments = aliments;
	}

	public Repas() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHeure() {
		return heure;
	}

	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}
	
	public String[] getAliments() {
		return aliments;
	}

	public void setAliments(String[] aliments) {
		this.aliments = aliments;
	}


	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	@Override
	public String toString() {
		return "Repas [identifiant=" + identifiant + ", date=" + date + ", heure=" + heure + ", aliments="
				+ Arrays.toString(aliments) + "]";
	}

	

	
	
	
	
	
}
