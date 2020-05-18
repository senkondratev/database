package Database.Controller.Insert.InsertSupport;

import Database.Domain.Guest;
import Database.Domain.Service;
import Database.Repository.GuestRepository;
import Database.Repository.ReceiptRepository;
import Database.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ReceiptInsertSupportController {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ServiceRepository serviceRepository;


    @GetMapping("insert/receiptInsertSupportController")
    public String rg(int selectedGuestId, Map<String, Object> model){
        Guest tmpGuest = guestRepository.findByGuestId(selectedGuestId);
        Iterable<Service> itService = serviceRepository.findByBuilding_BuildingId(tmpGuest.getBuildingId());
        model.put("services", itService);

        return "/Insert/InsertSupport/receiptInsertSupport";
    }
}

