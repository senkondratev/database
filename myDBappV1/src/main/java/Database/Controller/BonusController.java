package Database.Controller;

import Database.Domain.Bonus;
import Database.Domain.Company;
import Database.Domain.Contract;
import Database.Repository.BonusRepository;
import Database.Repository.CompanyRepository;
import Database.Repository.ContractRepository;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BonusController {
    @Autowired
    private BonusRepository bonusRepository;
    @Autowired
    private ContractRepository contractRepository;

    public void createIterators(Map<String, Object> model){
        Iterable<Bonus> itBonus = bonusRepository.findAll();
        model.put("bonuses", itBonus);

        Iterable<Contract> itContract = contractRepository.findAll();
        model.put("contracts", itContract);
    }

    @GetMapping("/insert/bonus")
    public String bonus(Map<String, Object> model){
        createIterators(model);

        return "bonusInsertPage";
    }
    @PostMapping("/insert/bonus")
    public String addBonus(@RequestParam int contractId,
                           @RequestParam String bonusTXT,

                           Map<String, Object> model){
        Contract tmpContract = contractRepository.findByContractId(contractId);
        Bonus tmpBonus = new Bonus(tmpContract,bonusTXT);
        bonusRepository.save(tmpBonus);

        createIterators(model);

        return "bonusInsertPage";
    }
}
