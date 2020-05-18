package Database.Controller.Insert;

import Database.Domain.Building;
import Database.Repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BuildingInsertController {
    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping("/insert/building")
    public String building(Map<String, Object> model){
        Iterable<Building> it = buildingRepository.findAll();
        model.put("buildings", it);

        return "/Insert/buildingInsert";
    }

    @PostMapping("/insert/building")
    public String addBuilding(@RequestParam int lvl,
                              @RequestParam int height,
                              Map<String, Object> model){
        Building tmpBuilding = new Building(lvl, height);
        buildingRepository.save(tmpBuilding);

        Iterable<Building> it = buildingRepository.findAll();
        model.put("buildings", it);

        return "/Insert/buildingInsert";
    }
}
