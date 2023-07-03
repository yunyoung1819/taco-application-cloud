package taco.ingredientclient.resttemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	private IngredientServiceClient client;

	public IngredientController(IngredientServiceClient client) {
		this.client = client;
	}

	@GetMapping
	public String ingredientList(Model model) {
		model.addAttribute("ingredients", client.getAllIngredients());
		return "ingredientList";
	}

	@GetMapping("/{id}")
	public String ingredientDetailPage(@PathVariable("id") String id, Model model) {
		model.addAttribute("ingredient", client.getIngredientById(id));
		return "ingredientDetail";
	}
}
