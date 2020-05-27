package Database.Repository;

import Database.Domain.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    public Room findByRoomId(int id);
    public List<Room> findAllByBuilding_BuildingId(int id);
}
