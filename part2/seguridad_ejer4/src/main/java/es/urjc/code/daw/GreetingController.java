package es.urjc.code.daw;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(Model model, @RequestParam String name) {

		PolicyFactory sanitizer = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);
		model.addAttribute("name",sanitizer.sanitize( name));

		return "greeting_template";
	}

}
