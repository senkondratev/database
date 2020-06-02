package Database.Controller.Select;

import Database.Domain.Building;
import Database.Domain.Client;
import Database.Domain.Guest;
import Database.Repository.BuildingRepository;
import Database.Repository.ClientRepository;
import Database.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class twelfthController {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/select/twelfth")
    public String twelfth(Map<String, Object> model){
        Iterable<Building> itBuilding = buildingRepository.findAll();
        model.put("buildings", itBuilding);
        model.put("status", "Выберите здание, если нужно, и нажмите окей");
        return "/select/twelfth/twelfth";
    }
    @GetMapping("/select/twelfthSelect")
    public String twelfthSelect(@RequestParam(required = false) Integer buildingId,
                                Map<String,Object> model){
        List<Client> clientList = (List<Client>) clientRepository.findAll();
        List<Client> bestClientList = new ArrayList<>();
        int max = 0;
        if(buildingId!=null){
            for(Client client:clientList){
                List<Guest> guestList = guestRepository.findByClient_ClientIdAndReservation_Building_BuildingId(client.getClientID(), buildingId);
                if(guestList.size() > max){
                    max = guestList.size();
                    bestClientList.clear();
                    bestClientList.add(client);
                }
                else if(guestList.size() == max){
                    bestClientList.add(client);
                }
            }
        }else{
            for(Client client:clientList){
                List<Guest> guestList = guestRepository.findByClient_ClientId(client.getClientID());
                if(guestList.size() > max){
                    max = guestList.size();
                    bestClientList.clear();
                    bestClientList.add(client);
                }
                else if(guestList.size() == max){
                    bestClientList.add(client);
                }
            }
        }
        model.put("status", String.valueOf(max)+" столько раз была посещена гостиница");
        model.put("clients", bestClientList);
        Iterable<Building> itBuilding = buildingRepository.findAll();
        model.put("buildings", itBuilding);
        return "/select/twelfth/twelfth";
    }
}
