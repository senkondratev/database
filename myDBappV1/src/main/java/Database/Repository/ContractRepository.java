package Database.Repository;

import Database.Domain.Contract;
import org.springframework.data.repository.CrudRepository;

public interface ContractRepository extends CrudRepository<Contract, Integer> {
    public Contract findByContractId(int id);
}
