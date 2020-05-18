package Database.Domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bonusId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contractId", referencedColumnName = "contractId")
    private Contract contract;

    private String bonus;

    public Bonus() {
    }

    public Bonus(Contract c, String b) {
        contract = c;
        bonus = b;
    }
    //группа геттеров для "красивого вывода" - геттеры "чужих" полей
    public int getContractId(){
        return contract.getContractId();
    }
    public String getCompanyName(){
        return contract.getCompanyName();
    }

    //геттеры полей этого класса
    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getBonusId() {
        return bonusId;
    }

    public void setBonusId(int bonusId) {
        this.bonusId = bonusId;
    }
}
