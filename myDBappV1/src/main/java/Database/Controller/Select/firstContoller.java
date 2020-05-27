package Database.Controller.Select;

import Database.Domain.Company;
import Database.Repository.CompanyRepository;
import Database.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class firstContoller {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/select/first")
    public String first(Map<String, Object> model){
        List<Company> itCompany = (List<Company>) companyRepository.findAll();
        model.put("companies", itCompany);
        model.put("count", itCompany.size());
        model.put("status", "Ошибок нет");
        return "/select/first/first";
    }

    @GetMapping("/select/firstSelect")
    public String firstSelect(@RequestParam int volume,
                              @RequestParam(required = false) Date d1,
                              @RequestParam(required = false) Date d2,
                              Map<String,Object> model){
        if((d1 == null)&&(d2 == null)){
            Iterable<Company> itCompany = companyRepository.findAll();
            int count = 0;
            List<Company> companyList = new ArrayList<>();
            for (Company c:itCompany){
                if(guestRepository.findByCompany_CompanyId(c.getCompanyId()).size() >= volume){
                    companyList.add(c);
                }
            }
            model.put("companies", companyList);
            model.put("count", companyList.size());
            model.put("status", "Ошибок нет");
        }
        else {
            if (d1.before(d2)) {
                Iterable<Company> itCompany = companyRepository.findAll();
                int count = 0;
                List<Company> companyList = new ArrayList<>();
                for (Company c : itCompany) {
                    if ((guestRepository.findByCompany_CompanyIdAndReservation_StartDateAfterAndReservation_EndDateBefore(c.getCompanyId(), d1, d2).size()) >= volume) {
                        companyList.add(c);
                    }
                }
                model.put("companies", companyList);
                model.put("count", companyList.size());
                model.put("status", "Ошибок нет");
            }
            else{
                List<Company> itCompany = (List<Company>) companyRepository.findAll();
                model.put("companies", itCompany);
                model.put("count", itCompany.size());
                model.put("status", "Вы перепутали даты");
            }
        }
        return "select/first/first";
    }

    @GetMapping("/select/firstAddDates")
    public String addDates(){
        return "/select/first/addDates";
    }

    @GetMapping("/select/firstRemoveDates")
    public String removeDates(){
        System.out.println("ama here");
        return "/select/first/removeDates";
    }
}
