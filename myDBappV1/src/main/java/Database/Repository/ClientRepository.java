package Database.Repository;

import Database.Domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    public Client findByClientId(int id);

    //тринадцатый запрос
    public List<Client> findByFirstAppearanceAfterAndFirstAppearanceBefore(Date d1, Date d2);
}
