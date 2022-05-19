package fr.epsi.rennes.poec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.rennes.poec.domain.Panier;
import fr.epsi.rennes.poec.domain.Pizza;
import fr.epsi.rennes.poec.domain.Response;
import fr.epsi.rennes.poec.service.PanierService;
import fr.epsi.rennes.poec.service.PizzaService;

@RestController
public class IndexController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private PanierService panierService;
	
	@GetMapping("/public/pizzas")
	public Response<List<Pizza>> getAllPizza(){
		List<Pizza> pizzas = pizzaService.getAllPizza();
		Response<List<Pizza>> response = new Response<>();
		response.setData(pizzas);
		return response;
	}
	
	@PostMapping("/public/panier/pizza")
	public Response<Integer> addPizza(@RequestParam int pizzaId, @RequestParam int panierId){
		Pizza pizza = new Pizza();
		pizza.setId(pizzaId);;
		
		panierId = panierService.addPizza(pizza, panierId);
		Response<Integer> response = new Response<>();
		response.setData(panierId);
		return response;
	}
	@GetMapping("/public/panier")
	public Response<Panier> getPanier(@RequestParam int panierId){
		Panier panier = panierService.getPanierById(panierId);
		
		Response<Panier> response = new Response<>();
		response.setData(panier);
		return response;
		
	}

	
}