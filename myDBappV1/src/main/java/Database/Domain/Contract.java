package Database.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")
    private Company company;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "contractId")
    private List<Bonus> bonuses;

    public Contract() {
    }
    public Contract(Company c) {
        company = c;
    }

    //группа геттеров для "красивого вывода" - геттеры "чужих" полей
    public int getCompanyId(){
        return company.getCompanyId();
    }

    public String getCompanyName(){
        return company.getCompanyName();
    }

    //геттеры полей этого класса
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }
}

