package Database.Repository;

import Database.Domain.Guest;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
    public Guest findByGuestId(int i);
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfter(int i, Date d1, Date d2);
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateAfterAndReservation_EndDateBefore(int i, Date d1, Date d2);
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateAndReservation_EndDate(int i, Date d1, Date d2);
    public List<Guest> findByOrderByGuestId();

    //первый запрос
    public List<Guest> findByCompany_CompanyId(int i);
    public List<Guest> findByCompany_CompanyIdAndReservation_StartDateAfterAndReservation_EndDateBefore(int i, Date d1, Date d2);

    //второй запрос
    public List<Guest> findByReservation_StartDateAfterAndReservation_EndDateBefore(Date d1, Date d2);
    public List<Guest> findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBefore(int i, Date d1, Date d2);
    public List<Guest> findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfit(int i, Date d1, Date d2, int profit);
    public List<Guest> findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomFloor(int i, Date d1, Date d2, int floor);
    public List<Guest> findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomFloorAndRoom_RoomProfit(int i, Date d1, Date d2, int floor, int profit);
    public List<Guest> findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacity(int i, Date d1, Date d2, int capacity);
    public List<Guest> findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacityAndRoom_RoomProfit(int i, Date d1, Date d2, int capacity, int profit);
    public List<Guest> findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacityAndRoom_RoomFloor(int i, Date d1, Date d2, int capacity, int floor);
    public List<Guest> findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacityAndRoom_RoomFloorAndRoom_RoomProfit(int i, Date d1, Date d2, int capacity, int floor, int profit);
    public List<Guest> findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfit(Date d1, Date d2, int profit);
    public List<Guest> findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfitAndRoom_RoomFloor(Date d1, Date d2, int profit, int floor);
    public List<Guest> findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfitAndRoom_RoomCapacity(Date d1, Date d2, int profit, int capacity);
    public List<Guest> findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfitAndRoom_RoomCapacityAndRoom_RoomFloor(Date d1, Date d2, int profit, int capacity, int floor);
    public List<Guest> findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomFloor(Date d1, Date d2, int floor);
    public List<Guest> findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomFloorAndRoom_RoomCapacity(Date d1, Date d2, int floor, int capacity);
    public List<Guest> findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacity(Date d1, Date d2, int capacity);
}
