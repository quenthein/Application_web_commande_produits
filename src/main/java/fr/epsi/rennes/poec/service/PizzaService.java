package fr.epsi.rennes.poec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.rennes.poec.dao.PizzaDao;
import fr.epsi.rennes.poec.domain.Panier;
import fr.epsi.rennes.poec.domain.Pizza;
import fr.epsi.rennes.poec.exception.BusinessException;


@Service
public class PizzaService {

    @Autowired
    private PizzaDao pizzaDAO;
    
    public List<Pizza> getAllPizzas(){
		List<Pizza> pizzas = pizzaDAO.getAllPizzas();
		return pizzas;
	}
	
	public List<Pizza> getAllPizza(){
		List<Pizza> pizzas = pizzaDAO.getAllPizza();
		return pizzas;
	}

    public void createPizza(Pizza pizza) throws BusinessException{
        if(pizza.getLibelle() == null) {
            throw new BusinessException("pizza.libelle.null");
        }
        int pizzaId = pizzaDAO.createPizza(pizza.getLibelle());
        for (int i = 0; i < pizza.getIngredients().size(); i++) {
        	int IngredientId = pizza.getIngredients().get(i).getId();
        	pizzaDAO.addIngredientToPizza(pizzaId, IngredientId);
        }
    }

    public void addIngredientToPizza(int pizzaId, int ingredientId) throws BusinessException{
        if(pizzaId < 0 || ingredientId < 0)
        {
            throw new BusinessException("pizza.libelle.null");
        }
        pizzaDAO.addIngredientToPizza(pizzaId, ingredientId);
    }
    
    
    public Panier getPanierById(int panierId) {
    	Panier panier = panierDAO.getPanierById(panierId);
    	List<Pizza> pizzas = panier.getPizzas();
    	double prixPizza = 0;
    	for (int i = 0; i < pizzas.size(); i++) {
    	Pizza pizza = pizza.get(i);
    	
    	}
    return panier;
    }
    
}


