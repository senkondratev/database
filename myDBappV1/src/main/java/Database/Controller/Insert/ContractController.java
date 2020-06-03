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
public class ContractController {
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

        return "/insert/contract/contract";
    }

    @PostMapping("/insert/contract")
    public String addContract(@RequestParam int companyId, Map<String, Object> model){
        Company tmpCompany = companyRepository.findByCompanyId(companyId);
        Contract tmpContract = new Contract(tmpCompany);
        contractRepository.save(tmpContract);


        createIterators(model);
        return "/insert/contract/contract";
    }

    @PostMapping("insert/contractDelete")
    public String deleteContract(@RequestParam int contractId, Map<String, Object> model){
        Contract contract = contractRepository.findByContractId(contractId);
        contractRepository.delete(contract);

        createIterators(model);
        return "/insert/contract/contractDelete";
    }
}
