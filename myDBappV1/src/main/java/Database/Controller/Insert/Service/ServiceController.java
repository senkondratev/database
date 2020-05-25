package Database.Controller.Insert.Service;


import Database.Domain.Building;
import Database.Domain.Service;
import Database.Repository.BuildingRepository;
import Database.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    public void createIterators(Map<String,Object> model){
        Iterable<Service> itService = serviceRepository.findAll();
        model.put("services", itService);

        Iterable<Building> itBuilding = buildingRepository.findAll();
        model.put("buildings", itBuilding);
    }

    @GetMapping("/insert/service")
    public String service(Map<String, Object> model){
        createIterators(model);

        return "/insert/service/service";
    }

    @PostMapping("/insert/service")
    public String addService(@RequestParam int buildingId,
                             @RequestParam int servicePrice,
                             @RequestParam String serviceName,
                             Map<String,Object> model){
        Building tmpBuilding = buildingRepository.findByBuildingId(buildingId);
        Service tmpService = new Service(tmpBuilding,servicePrice,serviceName);
        serviceRepository.save(tmpService);

        createIterators(model);

        return "/insert/service/service";
    }

    @PostMapping("/insert/serviceDelete")
    public String deleteService(@RequestParam int serviceId, Map<String, Object> model){
        Service s = serviceRepository.findByServiceId(serviceId);
        serviceRepository.delete(s);

        Iterable<Service> it = serviceRepository.findAll();
        model.put("services", it);

        return "/insert/service/serviceDelete";
    }
}
