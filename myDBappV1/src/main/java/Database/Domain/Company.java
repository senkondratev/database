package Database.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    private String companyName;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "companyId")
    private List<Contract> contract;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "companyId")
    private List<Guest> guests;

    public Company() {
    }
    public Company(String name) {
        companyName = name;
    }

    //геттеры полей этого класса
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
