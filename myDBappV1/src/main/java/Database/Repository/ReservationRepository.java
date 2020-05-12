package Database.Repository;

import Database.Domain.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    public Reservation findByReservationId(int id);
}
