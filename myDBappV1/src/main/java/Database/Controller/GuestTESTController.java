package Database.Controller;

import Database.Domain.Room;
import Database.Repository.ReservationRepository;
import Database.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GuestTESTController {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;
    @GetMapping("/insert/guestReservationTEST")
    public String gogo(int selectedReservationId, Map<String, Object> model){
        Iterable<Room> itRoom = roomRepository.findAllByBuilding_BuildingId(reservationRepository.findByReservationId(selectedReservationId).getBuildingId());
        model.put("rooms", itRoom);

        return "guestReservationTEST";
    }
}
