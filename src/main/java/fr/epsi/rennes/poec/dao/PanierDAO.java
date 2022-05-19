package fr.epsi.rennes.poec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;
import fr.epsi.rennes.poec.domain.Ingredient;
import fr.epsi.rennes.poec.domain.Panier;
import fr.epsi.rennes.poec.domain.Pizza;
import fr.epsi.rennes.poec.exception.TechnicalException;


@Repository
public class PanierDAO {
	
	@Autowired
	private DataSource ds;
	
	private void logg
	
	public void addPizza(Pizza pizza, int panierId) {
		String sql = "insert into panier_pizza " +
					 " (panier_id, pizza_id) values (?, ?)";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
				
				ps.setInt(1, panierId);
				ps.setInt(2, pizza.getId());
				ps.executeUpdate();

		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
	
	}
	
	public boolean isPanierExists(int panierId) {
		String sql = "select id from panier where user_id = ? and id = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, panierId);
			
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
	}
	
	public int createPanier() {
		String sql = "inert into panier (date) values(?)";
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
			ps.setString(1, date);
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
		throw new TechnicalException(new SQLException("Panier creation error"));
	}
	
	public Panier getPanierById(int panierId) {
		String sql = "Select pizza.id as pizzaid, "
                + "pizza.libelle as pizzalibelle, "
                + "group_concat(ingredient.id) as ingredient "
                + "from pizza "
                + "right join pizza_has_ingredient "
                + "on pizza_has_ingredient.pizzaid = pizza.id "
                + "left join ingredient "
                + "on pizza_has_ingredient.ingredientid = ingredient.id "
                + "group by pizza.id;";
		
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, panierId);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Panier panier = new Panier();
				panier.setId(rs.getInt("panierId"));
				
				panier.setPizzas(new ArrayList<>());
String pizzas = rs.getString("pizzas");
				
				for (String pizzaId : pizzas.split(",")) {
					Pizza pizza = new Pizza();
					pizza.setId(Integer.parseInt(pizzaId));
					
					panier.getPizzas().add(pizza);
				}
				return panier;
			}
			return null;
			
		}catch (SQLException e) {
			throw new TechnicalException(e);
		}
		
	}
	
    public Pizza getPizzaById(int pizzaId) {
        String sql ="Select pizza.id as pizzaid, pizza.libelle as pizzalibelle, group_concat(ingredient.libelle) as ingredients from pizza "
                + "right join pizza_has_ingredient "
                + "on pizzaid = pizza_has_ingredient.pizzaid "
                + "left join ingredient "
                + "on ingredient.id = pizza_has_ingredient.ingredientid "
                + "where pizzaid = ? "
                + "group by pizza.id";
        
        try { (Connection conn = ds.getConnection();
              PreparedStatement ps = conn.prepareStatement(sql));
              ps.setInt(1,pizzaId);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	pizza Pizza = new Pizzas();
            	pizza.setId(rs.getInt("id"));
            	pizza.setLibelle(rs.getString("ingredients"));
            	pizza.setIngredients(new ArrayList<>());
            	
            	String ingredientsString = rs.getString("ingredients");
            	Logger.
            	
            	if (ingredientsString != null && ingredientsString.length() > 0) {
            		String[] ingredientsTab = ingredientsString.split("'");
            		for (String ingredient : ingredientsTab) {
            			String[] colonnes = ingredients.split("\\:");
            			Ingredient ingredientPojo = new Ingredient();
            			ingredientPojo.setId(pizzaId);
            		}
            	}
            
            }
            
            while (rs.next()) {
                
            }
        } catch(SQLException e) {
            throw new TechnicalException(e);
        }
    }
}
