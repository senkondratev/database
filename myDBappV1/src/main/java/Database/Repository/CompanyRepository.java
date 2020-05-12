package Database.Repository;

import Database.Domain.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    public Company findByCompanyId(int id);
}
