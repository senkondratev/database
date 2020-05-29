package Database.Controller.Select;


import Database.Domain.Guest;
import Database.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class eighthController {
    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/select/eighth")
    public String eighth(Map<String, Object> model){
        List<Guest> guestList = guestRepository.findByReviewType(false);
        model.put("guests",  guestList);
        return "/select/eighth/eighth";
    }
}
