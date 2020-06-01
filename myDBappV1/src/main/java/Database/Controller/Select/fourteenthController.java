package Database.Controller.Select;

import Database.Domain.Client;
import Database.Domain.Guest;
import Database.Domain.Receipt;
import Database.Repository.ClientRepository;
import Database.Repository.GuestRepository;
import Database.Repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class fourteenthController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ReceiptRepository receiptRepository;


    @GetMapping("/select/fourteenth")
    public String fourteenth(Map<String, Object> model){
        Iterable<Client> itClient = clientRepository.findAll();
        model.put("clients", itClient);
        model.put("count", "Сначала выберите клиента");
        return "/select/fourteenth/fourteenth";
    }

    @GetMapping("/select/fourteenthSelect")
    public String fourteenthSelect(@RequestParam int clientId,
                                    Map<String, Object> model){
        List<Guest> guestList = guestRepository.findByClient_ClientId(clientId);
        model.put("count", String.valueOf(guestList.size())+" - число посещений гостиницы данным клиентом");
        List<myEntryFourteenth> myEntryFourteenthList = new ArrayList<>();//опять свой энтри для максимально удобного вывода
        List<Receipt> receiptList = new ArrayList<>();
        for(Guest guest: guestList){
            myEntryFourteenth m = new myEntryFourteenth(guest.getRoomId(),guest.getStartDate(),guest.getEndDate());
            myEntryFourteenthList.add(m);
            List<Receipt> rL = receiptRepository.findByGuest_GuestId(guest.getGuestId());
            receiptList.addAll(rL);
        }
        model.put("roomsAndDates", myEntryFourteenthList);
        model.put("receipts", receiptList);


        Iterable<Client> itClient = clientRepository.findAll();
        model.put("clients", itClient);
        return "/select/fourteenth/fourteenth";
    }
}
