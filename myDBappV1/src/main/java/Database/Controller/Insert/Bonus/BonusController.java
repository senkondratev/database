package Database.Controller.Insert.Bonus;

import Database.Domain.Bonus;
import Database.Domain.Contract;
import Database.Repository.BonusRepository;
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
        //Iterable<Bonus> itBonus = bonusRepository.findByContract_Company_CompanyName("NSU");
        model.put("bonuses", itBonus);

        Iterable<Contract> itContract = contractRepository.findAll();
        model.put("contracts", itContract);
    }

    @GetMapping("/insert/bonus")
    public String bonus(Map<String, Object> model){
        createIterators(model);

        return "/insert/bonus/bonus";
    }
    @PostMapping("/insert/bonus")
    public String addBonus(@RequestParam int contractId,
                           @RequestParam String bonusTXT,

                           Map<String, Object> model){
        Contract tmpContract = contractRepository.findByContractId(contractId);
        Bonus tmpBonus = new Bonus(tmpContract,bonusTXT);
        bonusRepository.save(tmpBonus);

        createIterators(model);

        return "/insert/bonus/bonus";
    }

    @PostMapping("/insert/bonusDelete")
    public String deleteBonus(@RequestParam int bonusId, Map<String, Object> model){
        Bonus b = bonusRepository.findByBonusId(bonusId);
        bonusRepository.delete(b);

        Iterable<Bonus> it = bonusRepository.findAll();
        model.put("bonuses", it);
        return "/insert/bonus/bonusDelete";
    }
}
