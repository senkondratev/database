package Database.Controller.Insert;

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
public class RoomInsertController {
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

        return "/Insert/roomInsert";
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

        return "/Insert/roomInsert";
    }
}
