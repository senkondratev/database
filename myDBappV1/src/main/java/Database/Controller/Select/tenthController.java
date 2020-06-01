package Database.Controller.Select;

import Database.Domain.Guest;
import Database.Domain.Receipt;
import Database.Domain.Room;
import Database.Domain.Service;
import Database.Repository.GuestRepository;
import Database.Repository.ReceiptRepository;
import Database.Repository.RoomRepository;
import Database.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class tenthController {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/select/tenth")
    public String tenth(Map<String, Object> model){
        Iterable<Room> itRoom = roomRepository.findAll();
        model.put("rooms",itRoom);
        model.put("review", "Для получения жалоб выберите постояльца");
        model.put("money", "Для получения трат выберите постояльца");
        return "/select/tenth/tenth";
    }

    @GetMapping("/select/tenthSelect")
    public String tenthSelect(@RequestParam int roomId,
                              @RequestParam int guestId,
                              Map<String,Object> model){
        Guest guest = guestRepository.findByGuestId(guestId);
        if(!guest.isReviewType()){
            model.put("review", "Жалоба постояльца "+guest.getGuestReview());
        }
        else{
            model.put("review", "Жалоб не поступало");
        }

        List<Service> serviceList = new ArrayList<>();

        List<Receipt> receiptList = new ArrayList<>();
        receiptList = receiptRepository.findByGuest_GuestId(guestId);
        for (Receipt receipt:receiptList){
            serviceList.add(receipt.getService());
        }
        double sum = 0.0;
        for(Service s:serviceList){
            sum+=s.getServicePrice();
        }
        model.put("services", serviceList);

        model.put("money", String.valueOf(sum) + " -потраченные деньги на доп услуги");

        Iterable<Room> itRoom = roomRepository.findAll();
        model.put("rooms",itRoom);
        return "/select/tenth/tenth";
    }

    @GetMapping("/select/tenthValidate")
    public String tenthValidate(int selectedRoomId, Map<String, Object> model){
        Iterable<Guest> itGuest = guestRepository.findByRoom_RoomId(selectedRoomId);
        model.put("guests", itGuest);
        return "/select/tenth/tenthValidate";
    }
}
