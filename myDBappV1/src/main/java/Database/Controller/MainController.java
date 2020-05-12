package Database.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//First Page and Second Page
@Controller
public class MainController {
    @GetMapping("/")
    public String greetingPage() {
        return "main";
    }


    @GetMapping("/insert")
    public String insert() {
        return "insert";
    }
}