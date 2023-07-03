package tacos.ingredients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
public class IngredientController {

	private IngredientRepository repo;

	@Autowired
	public IngredientController(IngredientRepository repo) {
		this.repo = repo;
	}


	@GetMapping
	public Iterable<Ingredient> allIngredients() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Ingredient> byId(@PathVariable String id) {
		return repo.findById(id);
	}
}
