package Database.Controller.Select;

import Database.Domain.Company;
import Database.Domain.Guest;
import Database.Repository.CompanyRepository;
import Database.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class eleventhController {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/select/eleventh")
    public String eleventh(Map<String, Object> model){

        model.put("status", "Выберите сроки брони");
        return "/select/eleventh/eleventh";
    }

    @GetMapping("/select/eleventhSelect")
    public String eleventhSelect(@RequestParam Date d1,
                                 @RequestParam Date d2,
                                 Map<String, Object> model){
        if(d1.before(d2)) {
            List<Guest> guestList = guestRepository.findByReservation_StartDateAfterAndReservation_EndDateBefore(d1,d2);
            List<Company> companyList = new ArrayList<>();
            for (Guest guest:guestList){
                Company company = guest.getCompany();
                if(company!=null){
                    if(!companyList.contains(company)){
                        companyList.add(company);
                    }
                }
            }
            model.put("companies", companyList);
            model.put("status", "Выберите сроки брони");
        }
        else {
            model.put("status", "Вы перепутали даты");
        }
        return "/select/eleventh/eleventh";
    }
}
