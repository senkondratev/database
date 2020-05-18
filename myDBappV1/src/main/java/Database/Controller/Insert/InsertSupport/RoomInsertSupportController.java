package Database.Controller.Insert.InsertSupport;

import Database.Domain.Building;
import Database.Repository.BuildingRepository;
import Database.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RoomInsertSupportController {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping("/insert/roomInsertSupportController")
    public String roomTEST(int selectedBuildingId, Map<String, Object> model){
        System.out.println("i'm here");
        Building tmpBuilding  = buildingRepository.findByBuildingId(selectedBuildingId);
        int h = tmpBuilding.getBuildingHeight();
        model.put("max",h);

        return "/Insert/InsertSupport/roomInsertSupport";
    }
}

