package fr.epsi.rennes.poec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.rennes.poec.dao.PanierDAO;
import fr.epsi.rennes.poec.domain.Panier;
import fr.epsi.rennes.poec.domain.Pizza;


@Service
public class PanierService {
	@Autowired
	private PanierDAO panierDAO;
	
	public int addPizza(Pizza pizza, int panierId) {
		boolean exists= panierDAO.isPanierExists(panierId);
		if(!exists) {
			panierId = panierDAO.createPanier();
		}
		panierDAO.addPizza(pizza, panierId);
		return panierId;
	}
	
	public Panier getPanierById(int panierId) {
		return panierDAO.getPanierById(panierId);
	}
}
