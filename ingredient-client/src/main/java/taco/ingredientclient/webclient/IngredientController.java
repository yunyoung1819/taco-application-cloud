package taco.ingredientclient.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
@Profile("webclient")
@Slf4j
public class IngredientController {

	private IngredientServiceClient client;


}
