package Database.Controller.Select;

import Database.Domain.Guest;
import Database.Domain.Reservation;
import Database.Domain.Room;
import Database.Repository.GuestRepository;
import Database.Repository.ReservationRepository;
import Database.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class fifthController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private GuestRepository guestRepository;

    private List<Room> roomList = new ArrayList<>();
    @GetMapping("/select/fifth")
    public String fifth(Map<String, Object> model){
        roomList.clear();
        Iterable<Room> itRoom = roomRepository.findAll();
        int flag = 0;
        Date d = new Date(System.currentTimeMillis());
        for (Room r: itRoom){
            if(guestRepository.findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfter(r.getRoomId(),d, d).size() > 0){
                flag+=1;
            }
            if(guestRepository.findByRoom_RoomIdAndReservation_StartDate(r.getRoomId(),d).size()>0){
                flag+=1;
            }
            if(guestRepository.findByRoom_RoomIdAndReservation_EndDate(r.getRoomId(),d).size()>0){
                flag+=1;
            }
            if(flag == 0){
                roomList.add(r);
            }
            flag = 0;
        }
        model.put("rooms", roomList);
        return "/select/fifth/fifth";
    }

    @GetMapping("/select/fifthSelect")
    public String fifthSelect(@RequestParam int roomId,
                              Map<String, Object> model){
        //комната заведомо выбрана из числа свободных, можно не делать проверки
        Room r = roomRepository.findByRoomId(roomId);
        Date d = new Date(System.currentTimeMillis());
        List<Guest> listGuest = guestRepository.findByRoom_RoomIdAndReservation_StartDateAfter(roomId, d);
        if(listGuest.size()>0) {
            Date min = listGuest.get(0).getStartDate();
            for (Guest g : listGuest) {
                Date gd = g.getStartDate();
                if (gd.before(min)) {
                    min = gd;
                }
            }
            model.put("firstStart", min);

            long delt = (min.getTime() - d.getTime()) / 86400000 + 1; //+1 нужно, чтобы бронь, стартующая на следующий день считалась бронью, до которой остается ОДИН день, а  не ноль.
            model.put("timeLeft", delt);
        }
        else{
            model.put("firstStart", "В будущем нет броней данной комнаты");
            model.put("timeLeft", "Комната всегда свободна!");
        }
            model.put("chosenRoom", r);




            return "/select/fifth/fifthSelect";

    }
}
