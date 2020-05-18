package Database.Controller.Insert;

import Database.Domain.Client;
import Database.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Map;

@Controller
public class ClientInsertController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/insert/client")
    public String client(Map<String,Object> model){
        Iterable<Client> it = clientRepository.findAll();
        model.put("clients", it);

        return "/Insert/clientInsert";
    }

    @PostMapping("/insert/client")
    public String addClient(@RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam Date date,
                            Map<String, Object> model){
        Client tmpClient = new Client(name,surname,date);
        clientRepository.save(tmpClient);

        Iterable<Client> it = clientRepository.findAll();
        model.put("clients", it);

        return "/Insert/clientInsert";
    }
}
