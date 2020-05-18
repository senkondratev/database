package Database.Controller.Insert;

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
public class ReceiptInsertController {
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

        return "/Insert/receiptInsert";
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
        return "/Insert/receiptInsert";

    }
}
