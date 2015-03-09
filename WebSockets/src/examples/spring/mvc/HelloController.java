package examples.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
@RequestMapping("/mvc")
public class HelloController {
    private static Logger log = Logger.getLogger(HelloController.class.getName());
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        log.info("Somebody visited site");
        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "hello";

    }

}