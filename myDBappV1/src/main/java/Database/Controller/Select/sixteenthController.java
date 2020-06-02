package Database.Controller.Select;

import Database.Domain.Guest;
import Database.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class sixteenthController {
    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/select/sixteenth")
    public String sixteenth(Map<String, Object> model){
        List<Guest> guestList = (List<Guest>) guestRepository.findAll();
        double com = 0;
        double free = 0;
        for (Guest guest:guestList){
            if(guest.getCompany()!=null){
                com+=1;
            }
            else{
                free+=1;
            }
        }
        double ratio = com/(com+free)*100;
        model.put("ratio", "На данный момент процент посетителей от партнеров " + String.valueOf(ratio)+ "%");
        return "/select/sixteenth/sixteenth";
    }
}
