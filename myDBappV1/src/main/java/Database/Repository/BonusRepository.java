package Database.Repository;

import Database.Domain.Bonus;
import org.springframework.data.repository.CrudRepository;

public interface BonusRepository extends CrudRepository<Bonus, Integer> {
}
