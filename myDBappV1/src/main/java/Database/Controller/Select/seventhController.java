package Database.Controller.Select;

import Database.Domain.Company;
import Database.Domain.Guest;
import Database.Repository.CompanyRepository;
import Database.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class seventhController {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private GuestRepository guestRepository;


    @GetMapping("/select/seventh")
    public String seventh(Map<String, Object> model){
        Iterable<Company> itCompany = companyRepository.findAll();
        model.put("companies", itCompany);
        model.put("bestCount", "Сначала выберите компанию");
        model.put("bestRooms", "Комнаты не отобраны");
        return "/select/seventh/seventh";
    }

    @GetMapping("/select/seventhSelect")
    public String seventhSelect(@RequestParam int companyId,
                                @RequestParam Date startDate,
                                @RequestParam Date endDate,
                                Map<String, Object> model){
        if(startDate.before(endDate)) {
            List<Guest> guestList = guestRepository.findByCompany_CompanyIdAndReservation_StartDateAfterAndReservation_EndDateBefore(companyId, startDate, endDate);
            Map<Integer, Integer> roomPopularity = new HashMap<>();
            for (Guest g : guestList) {
                if (roomPopularity.get(g.getRoomId()) != null) {
                    roomPopularity.put(g.getRoomId(), roomPopularity.get(g.getRoomId()) + 1);
                } else {
                    roomPopularity.put(g.getRoomId(), 1);
                }
            }
            int max = 1;
            List<Integer> mostPopular = new ArrayList<>();
            for (Integer key : roomPopularity.keySet()) {
                if (roomPopularity.get(key) > max) {
                    max = roomPopularity.get(key);
                    mostPopular.clear();
                    mostPopular.add(key);
                } else if (roomPopularity.get(key) == max) {
                    mostPopular.add(key);
                }
            }
            model.put("bestCount", max);
            if (max != 1) {
                model.put("bestRooms", mostPopular);
            } else {
                model.put("bestRooms", "Все комнаты были заказаны единожды");
            }
        }
        else{
            model.put("bestCount", "Вы перепутали даты");
            model.put("bestRooms", "А ведь даты перепутаны...");
        }



        Iterable<Company> itCompany = companyRepository.findAll();
        model.put("companies", itCompany);
        return "/select/seventh/seventh";
    }
}
