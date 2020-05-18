package Database.Controller.Insert.InsertSupport;

import Database.Domain.Reservation;
import Database.Domain.Room;
import Database.Repository.GuestRepository;
import Database.Repository.ReservationRepository;
import Database.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GuestInsertSupportController {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;
    @GetMapping("/insert/guestInsertSupportController")
    public String gogo(int selectedReservationId, Map<String, Object> model){
        Reservation reservation = reservationRepository.findByReservationId(selectedReservationId);
        Iterable<Room> itRoom = roomRepository.findAllByBuilding_BuildingId(reservation.getBuildingId());
        Date sd = reservation.getStartDate();
        Date ed = reservation.getEndDate();
        List<Room> roomList = new ArrayList<>();
        int flag = 0;
        for(Room r: itRoom){
            if((guestRepository.findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfter(r.getRoomId(),sd,sd).size()) > 0){
                flag += 1;
            }
            if((guestRepository.findByRoom_RoomIdAndReservation_StartDateBeforeAndReservation_EndDateAfter(r.getRoomId(),ed,ed)).size() > 0){
                flag += 1;

            }
            if((guestRepository.findByRoom_RoomIdAndReservation_StartDateAfterAndReservation_EndDateBefore(r.getRoomId(),sd,ed)).size() > 0){
                flag += 1;
            }
            if(flag == 0){
                roomList.add(r);
            }
            flag = 0;
        }
        model.put("rooms", roomList);

        return "/Insert/InsertSupport/guestInsertSupport";
    }
}
