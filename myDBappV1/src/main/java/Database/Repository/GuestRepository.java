package Database.Repository;

import Database.Domain.Guest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
    public Guest findByGuestId(int i);
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfter(int i, Date d1, Date d2);
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateAfterAndReservation_EndDateBefore(int i, Date d1, Date d2);
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateAndReservation_EndDate(int i, Date d1, Date d2);
    public List<Guest> findByOrderByGuestId();
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfterOrRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfterOrRoom_RoomIdAndReservation_StartDateAfterAndReservation_EndDateBefore(int i, Date d1, Date d2, int j,Date d3, Date d4, int k, Date d5, Date d6);
    //огромный и страшный метод работает, но от этого не перестает быть плохим.

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

    //доп проверки - багофикс
    public List<Guest> findByRoom_RoomIdAndReservation_StartDate(int roomId, Date startDate);
    public List<Guest> findByRoom_RoomIdAndReservation_EndDate(int roomId, Date endDate);
    public List<Guest> findByRoom_RoomIdAndReservation_EndDateAndReservation_ReservationIdNot(int roomId, Date endDate, int resId);
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateAndReservation_ReservationIdNot(int roomId, Date startDate, int resId);

    //пятый запрос
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateAfter(int roomId, Date startDate);

    //шестой запрос
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfterAndReservation_EndDateBefore(int roomId, Date d1, Date d2, Date d3);
    public List<Guest> findByRoom_RoomIdAndReservation_StartDateAndReservation_EndDateBefore(int roomId, Date d1, Date d2);
    public List<Guest> findByRoom_RoomIdAndReservation_EndDateAndReservation_EndDateBefore(int roomId, Date d1, Date d2);


    //восьмой запрос
    public List<Guest> findByReviewType(boolean type);

    //десятый запрос
    public List<Guest> findByRoom_RoomId(int roomId);
}
