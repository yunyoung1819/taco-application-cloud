package taco.ingredientclient.resttemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import taco.ingredientclient.Ingredient;

import java.util.ArrayList;
import java.util.List;

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

	@HystrixCommand(fallbackMethod = "getDefaultIngredients")
	public Iterable<Ingredient> getAllIngredients() {
		ParameterizedTypeReference<List<Ingredient>> stringList =
				new ParameterizedTypeReference<List<Ingredient>>() {};
		return rest.exchange(
				"http://ingredient-service/ingredients", HttpMethod.GET,
				HttpEntity.EMPTY, stringList).getBody();
	}

	private Iterable<Ingredient> getDefaultIngredients() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
		ingredients.add(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
		ingredients.add(new Ingredient("CHED", "Shredded Cheddar", Ingredient.Type.CHEESE));
		return ingredients;
	}
}
