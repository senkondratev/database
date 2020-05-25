package Database.Controller.Insert.Room;

import Database.Domain.Building;
import Database.Domain.Room;
import Database.Repository.BuildingRepository;
import Database.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public void createIterators(Map<String, Object> model){
        Iterable<Room> itRoom = roomRepository.findAll();
        model.put("rooms", itRoom);

        Iterable<Building> itBuilding = buildingRepository.findAll();
        model.put("buildings", itBuilding);
    }

    @GetMapping("/insert/room")
    public String room(Map<String, Object> model){
        createIterators(model);

        return "/insert/room/room";
    }

    @PostMapping("/insert/room")
    public String addRoom(@RequestParam int buildingId,
                          @RequestParam int profit,
                          @RequestParam int floor,
                          @RequestParam int capacity,
                          Map<String, Object> model){
        Building tmpBuilding = buildingRepository.findByBuildingId(buildingId);
        Room tmpRoom = new Room(tmpBuilding, profit, floor, capacity);
        roomRepository.save(tmpRoom);

        createIterators(model);

        return "/insert/room/room";
    }

    //даем только правильные вещи на выбор в спике
    @GetMapping("/insert/roomValidate")
    public String roomTEST(int selectedBuildingId, Map<String, Object> model){
        System.out.println("i'm here");
        Building tmpBuilding  = buildingRepository.findByBuildingId(selectedBuildingId);
        int h = tmpBuilding.getBuildingHeight();
        model.put("max",h);

        return "/insert/room/roomValidate";
    }

    @PostMapping("/insert/roomDelete")
    public String deleteRoom(@RequestParam int roomId, Map<String, Object> model){
        Room r = roomRepository.findByRoomId(roomId);
        roomRepository.delete(r);

        Iterable<Room> it = roomRepository.findAll();
        model.put("rooms", it);

        return "/insert/room/roomDelete";
    }
}
