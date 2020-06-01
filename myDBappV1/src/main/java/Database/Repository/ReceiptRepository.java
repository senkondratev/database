package Database.Repository;

import Database.Domain.Receipt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer> {
    public Receipt findByReceiptId(int i);

    public List<Receipt> findByGuest_GuestId(int guestId);
}
