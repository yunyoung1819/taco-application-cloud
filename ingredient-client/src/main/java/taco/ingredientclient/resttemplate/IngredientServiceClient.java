package taco.ingredientclient.resttemplate;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import taco.ingredientclient.Ingredient;

import java.util.Arrays;

@Service
public class IngredientServiceClient {

	private RestTemplate rest;

	public IngredientServiceClient(@LoadBalanced RestTemplate rest) {
		this.rest = rest;
	}

	public Ingredient getIngredientById(String ingredientId) {
		return rest.getForObject(
				"http://ingredient-service/ingredients/{id}",
				Ingredient.class, ingredientId);
	}

	public Object getAllIngredients() {
		Ingredient[] ingredients = rest.getForObject(
				"http://ingredient-service/ingredients", Ingredient[].class);
		return Arrays.asList(ingredients);
	}
}
