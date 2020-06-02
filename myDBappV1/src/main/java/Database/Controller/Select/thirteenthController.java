package Database.Controller.Select;

import Database.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Map;

@Controller
public class thirteenthController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/select/thirteenth")
    public String thirteenth(Map<String, Object> model){
        model.put("status", "Для получения информации выберите даты");
        return "/select/thirteenth/thirteenth";
    }

    @GetMapping("/select/thirteenthSelect")
    public String thirteenthSelect(@RequestParam Date d1,
                                    @RequestParam Date d2,
                                    Map<String, Object> model){
        if(d1.before(d2)){
            model.put("clients", clientRepository.findByFirstAppearanceAfterAndFirstAppearanceBefore(d1, d2));
            model.put("status", "Выведены новые клиенты за выбранный период");
        }
        else {
            model.put("status", "Вы перепутали даты");
        }
        return "/select/thirteenth/thirteenth";
    }
}
