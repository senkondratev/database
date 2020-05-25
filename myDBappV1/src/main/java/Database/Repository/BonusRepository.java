package Database.Repository;

import Database.Domain.Bonus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BonusRepository extends CrudRepository<Bonus, Integer> {
    public List<Bonus> findByContract_Company_CompanyName(String s);
    public Bonus findByBonusId(int i);
}
