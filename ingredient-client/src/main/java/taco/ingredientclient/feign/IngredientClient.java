package taco.ingredientclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import taco.ingredientclient.Ingredient;

@FeignClient("ingredient-service")
public interface IngredientClient {

	@GetMapping("/ingredients")
	Iterable<Ingredient> getAllIngredients();

	@GetMapping("/ingredients/{id}")
	Ingredient getIngredient(@PathVariable("id") String id);

}
