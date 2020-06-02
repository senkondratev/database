package Database.Repository;

import Database.Domain.Room;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    public Room findByRoomId(int id);
    public List<Room> findAllByBuilding_BuildingId(int id);

    //четвертый запрос
    public List<Room> findByRoomCapacity(int capacity);
    public List<Room> findByRoomCapacityAndBuilding_BuildingId(int capacity,int buildingId);
    public List<Room> findByRoomCapacityAndRoomProfit(int capacity, int profit);
    public List<Room> findByRoomCapacityAndRoomProfitAndBuilding_BuildingId(int capacity, int profit, int buildingId);
    public List<Room> findByRoomCapacityAndRoomFloor(int capacity, int floor);
    public List<Room> findByRoomCapacityAndRoomFloorAndBuilding_BuildingId(int capacity, int floor, int buildingId);
    public List<Room> findByRoomCapacityAndRoomFloorAndRoomProfit(int capacity, int floor, int profit);
    public List<Room> findByRoomCapacityAndRoomFloorAndRoomProfitAndBuilding_BuildingId(int capacity, int floor, int profit, int buildingId);

    public List<Room> findByRoomFloor(int floor);
    public List<Room> findByRoomFloorAndBuilding_BuildingId(int floor, int buildingId);
    public List<Room> findByRoomFloorAndBuilding_BuildingIdAndRoomProfit(int floor, int buildingId, int profit);
    public List<Room> findByRoomFloorAndRoomProfit(int floor, int profit);

    public List<Room> findByBuilding_BuildingId(int buildingId);
    public List<Room> findByRoomProfit(int profit);
    public List<Room> findByRoomProfitAndBuilding_BuildingId(int profit, int buildingId);

    public List<Room> findAll(Sort by);
}
