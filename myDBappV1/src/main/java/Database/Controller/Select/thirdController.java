package Database.Controller.Select;

import Database.Domain.Room;
import Database.Repository.GuestRepository;
import Database.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class thirdController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/select/third")
    public String third(Map<String, Object> model){
        Iterable<Room> itRoom = roomRepository.findAll();
        int flag = 0;
        List<Room> roomList = new ArrayList<>();
        Date d = new Date(System.currentTimeMillis());
        for (Room r: itRoom){
            if(guestRepository.findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfter(r.getRoomId(),d, d).size() > 0){
                flag+=1;
            }
            if(flag == 0){
                roomList.add(r);
            }
            flag = 0;
        }
        model.put("rooms", roomList);
        model.put("count", roomList.size());
        return "/select/third/third";
    }
}
