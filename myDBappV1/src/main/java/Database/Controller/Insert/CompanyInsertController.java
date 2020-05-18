package Database.Controller.Insert;

import Database.Domain.Company;
import Database.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@Controller
public class CompanyInsertController {
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/insert/company")
    public  String company(Map<String, Object> model){
        Iterable<Company> it = companyRepository.findAll();
        model.put("companies", it);

        return "/Insert/companyInsert";
    }

    @PostMapping("/insert/company")
    public String addCompany(@RequestParam String name,Map<String, Object> model){
            Company tmpCompany = new Company(name);
            companyRepository.save(tmpCompany);

            Iterable<Company> it = companyRepository.findAll();
            model.put("companies", it);

            return "/Insert/companyInsert";
    }
}
