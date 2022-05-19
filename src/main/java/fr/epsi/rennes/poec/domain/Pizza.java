package fr.epsi.rennes.poec.domain;

import java.util.List;

public class Pizza {
	
	private int id;
	private String libelle;
	private double prix;
	private List<Ingredient> ingredients;
	private int nbCalories;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public int getNbCalories() {
		return nbCalories;
	}
	public void setNbCalories(int nbCalories) {
		this.nbCalories = nbCalories;
	}
	
}
