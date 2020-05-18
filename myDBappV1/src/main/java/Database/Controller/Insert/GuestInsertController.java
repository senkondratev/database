package Database.Controller.Insert;

import Database.Domain.*;
import Database.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GuestInsertController {
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

        return "/Insert/guestInsert";
    }

    @PostMapping("insert/guest")
    public String addGuest(@RequestParam int reservationId,
                           @RequestParam int companyId,
                           @RequestParam int clientId,
                           @RequestParam int roomId,
                           @RequestParam boolean reviewType,
                           @RequestParam String review,
                           Map<String, Object> model){
        Room tmpRoom = roomRepository.findByRoomId(roomId);

        Reservation tmpReservation = reservationRepository.findByReservationId(reservationId);
        Company tmpCompany = companyRepository.findByCompanyId(companyId);
        Client tmpClient = clientRepository.findByClientId(clientId);

        Guest tmpGuest = new Guest(tmpReservation, tmpCompany, tmpClient, tmpRoom, reviewType, review);
        guestRepository.save(tmpGuest);

        createIterators(model, "everything is ok");

        return "/Insert/guestInsert";
        }
    }

