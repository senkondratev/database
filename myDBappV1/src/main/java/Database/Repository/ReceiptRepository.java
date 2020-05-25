package Database.Repository;

import Database.Domain.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer> {
    public Receipt findByReceiptId(int i);
}
