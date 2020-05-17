package Database.Repository;

import Database.Domain.Guest;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfter(int i, Date d1, Date d2);
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateAfterAndReservation_EndDateBefore(int i, Date d1, Date d2);
}
