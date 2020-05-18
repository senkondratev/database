package Database.Controller;

import Database.Domain.Guest;
import Database.Domain.Receipt;
import Database.Domain.Service;
import Database.Repository.GuestRepository;
import Database.Repository.ReceiptRepository;
import Database.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ReceiptController {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public void createIterators(Map<String, Object> model){
        Iterable<Receipt> itReceipt = receiptRepository.findAll();
        model.put("receipts", itReceipt);

        Iterable<Guest> itGuest = guestRepository.findAll();
        model.put("guests", itGuest);

       // Iterable<Service> itService = serviceRepository.findAll();
       // model.put("services", itService);
    }

    @GetMapping("/insert/receipt")
    public String receipt(Map<String, Object> model){
        createIterators(model);

        return "receiptInsertPage";
    }

    @PostMapping("/insert/receipt")
    public String addReceipt(Map<String, Object> model,
                             @RequestParam int guestId,
                             @RequestParam int serviceId){
        Guest tmpGuest = guestRepository.findByGuestId(guestId);
        Service tmpService = serviceRepository.findByServiceId(serviceId);
        Receipt tmpReceipt = new Receipt(tmpGuest, tmpService);
        receiptRepository.save(tmpReceipt);

        createIterators(model);
        return "receiptInsertPage";

    }

    @GetMapping("insert/receiptTEST")
    public String rg(int selectedGuestId, Map<String, Object> model){
        Guest tmpGuest = guestRepository.findByGuestId(selectedGuestId);
        Iterable<Service> itService = serviceRepository.findByBuilding_BuildingId(tmpGuest.getBuildingId());
        model.put("services", itService);

        return "receiptHelpPage";
    }
}
