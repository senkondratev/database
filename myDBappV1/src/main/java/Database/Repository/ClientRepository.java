package Database.Repository;

import Database.Domain.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    public Client findByClientId(int id);
}
