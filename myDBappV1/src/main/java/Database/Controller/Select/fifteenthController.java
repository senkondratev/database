package Database.Controller.Select;

import Database.Domain.Guest;
import Database.Repository.GuestRepository;
import Database.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class fifteenthController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/select/fifteenth")
    public String fifteenth(Map<String, Object> model){
        model.put("rooms", roomRepository.findAll());
        return "/select/fifteenth/fifteenth";
    }

    @GetMapping("/select/fifteenthSelect")
    public String fifteenthSelect(@RequestParam int roomId,
                                  @RequestParam Date d1,
                                  @RequestParam Date d2,
                                  Map<String, Object> model){
        List<Guest> guestList = guestRepository.findByRoom_RoomIdAndReservation_StartDateAfterAndReservation_EndDateBefore(roomId,d1,d2);
        model.put("guests", guestList);

        model.put("rooms", roomRepository.findAll());
        return "/select/fifteenth/fifteenth";
    }
}
