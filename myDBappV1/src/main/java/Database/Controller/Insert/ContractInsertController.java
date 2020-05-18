package Database.Controller.Insert;

import Database.Domain.Company;
import Database.Domain.Contract;
import Database.Repository.CompanyRepository;
import Database.Repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ContractInsertController {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CompanyRepository companyRepository;

    private void createIterators(Map<String, Object> model){
        Iterable<Contract> itContract = contractRepository.findAll();
        model.put("contracts", itContract);

        Iterable<Company> itCompany = companyRepository.findAll();
        model.put("companies", itCompany);
    }

    @GetMapping("/insert/contract")
    public String contract(Map<String, Object> model){
        createIterators(model);

        return "/Insert/contractInsert";
    }

    @PostMapping("/insert/contract")
    public String addContract(@RequestParam int companyId, Map<String, Object> model){
        Company tmpCompany = companyRepository.findByCompanyId(companyId);
        Contract tmpContract = new Contract(tmpCompany);
        contractRepository.save(tmpContract);


        createIterators(model);
        return "/Insert/contractInsert";
    }
}
