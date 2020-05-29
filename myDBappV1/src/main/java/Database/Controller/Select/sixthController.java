package Database.Controller.Select;

import Database.Domain.Room;
import Database.Repository.GuestRepository;
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
public class sixthController {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/select/sixth")
    public String sixth(Map<String, Object> model){
        Iterable<Room> itRoom = roomRepository.findAll();
        int flag = 0;
        List<Room> roomList = new ArrayList<>();
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
            if(flag != 0){
                roomList.add(r);
            }
            flag = 0;
        }
        model.put("whatisit", "выведены все занятые комнаты");
        model.put("rooms", roomList);
        return "/select/sixth/sixth";
    }

    @GetMapping("/select/sixthSelect")
    public String sixthSelect(@RequestParam Date endDate,
                              Map<String, Object> model){

        Iterable<Room> itRoom = roomRepository.findAll();
        int flag = 0;
        List<Room> roomList = new ArrayList<>();
        Date d = new Date(System.currentTimeMillis());
        for (Room r: itRoom){
            if(guestRepository.findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfterAndReservation_EndDateBefore(r.getRoomId(),d, d,endDate).size() > 0){
                flag+=1;
            }
            if(guestRepository.findByRoom_RoomIdAndReservation_StartDateAndReservation_EndDateBefore(r.getRoomId(),d , endDate).size()>0){
                flag+=1;
            }
            if(guestRepository.findByRoom_RoomIdAndReservation_EndDateAndReservation_EndDateBefore(r.getRoomId(),d, endDate).size()>0){
                flag+=1;
            }
            if(flag != 0){
                roomList.add(r);
            }
            flag = 0;
        }
        model.put("whatisit", "Выведены занятые комнаты, которые освободятся к указанному сроку");
        model.put("rooms", roomList);
        return "/select/sixth/sixth";
    }
}
