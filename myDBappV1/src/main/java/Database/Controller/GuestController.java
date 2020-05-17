package Database.Controller;

import Database.Domain.*;
import Database.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Map;

@Controller
public class GuestController {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RoomRepository roomRepository;

    public void createIterators(Map<String, Object> model, String status){
        Iterable<Guest> itGuest = guestRepository.findAll();
        model.put("guests", itGuest);

        Iterable<Reservation> itReservation = reservationRepository.findAll();
        model.put("reservations", itReservation);

        Iterable<Company> itCompany = companyRepository.findAll();
        model.put("companies", itCompany);

        Iterable<Client> itClient =clientRepository.findAll();
        model.put("clients", itClient);

        //Iterable<Room> itRoom = roomRepository.findAll();
       // model.put("rooms", itRoom);

        model.put("status", status);
    }

    @GetMapping("/insert/guest")
    public String guest(Map<String, Object> model){
        createIterators(model,"everything is ok");

        return "guestInsertPage";
    }

    @PostMapping("insert/guest")
    public String addGuest(@RequestParam int reservationId,
                           @RequestParam int companyId,
                           @RequestParam int clientId,
                           @RequestParam int roomId,
                           @RequestParam boolean reviewType,
                           @RequestParam String review,
                           @RequestParam boolean flag,
                           Map<String, Object> model){
        Room tmpRoom = roomRepository.findByRoomId(roomId);
        if((tmpRoom.getRoomCurrentGuestCount() < tmpRoom.getRoomCapacity())&&(tmpRoom.isEmpty())) {

            Reservation tmpReservation = reservationRepository.findByReservationId(reservationId);
            Company tmpCompany = companyRepository.findByCompanyId(companyId);
            Client tmpClient = clientRepository.findByClientId(clientId);

            //манипуляции с комнатой
            int curGuestCount = tmpRoom.getRoomCurrentGuestCount()+1;
            tmpRoom.setRoomCurrentGuestCount(curGuestCount);
            if(curGuestCount == tmpRoom.getRoomCapacity()){
                tmpRoom.setEmpty(false);
            }
            if (flag){
                tmpRoom.setEmpty(false);
            }
            //конец манипуляций с комнатой

            Guest tmpGuest = new Guest(tmpReservation, tmpCompany, tmpClient, tmpRoom, reviewType, review);
            guestRepository.save(tmpGuest);

            createIterators(model, "everything is ok");

            return "guestInsertPage";
        }
        else {
            createIterators(model,"room is already full or notEmpty");

            return "guestInsertPage";
        }
    }
}
