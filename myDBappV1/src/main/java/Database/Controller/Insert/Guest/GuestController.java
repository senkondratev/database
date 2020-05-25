package Database.Controller.Insert.Guest;

import Database.Domain.*;
import Database.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

    public void createIterators(Map<String, Object> model){
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

    }

    @GetMapping("/insert/guest")
    public String guest(Map<String, Object> model){
        createIterators(model);

        return "/insert/guest/guest";
    }

    @PostMapping("insert/guest")
    public String addGuest(@RequestParam int reservationId,
                           @RequestParam int companyId,
                           @RequestParam int clientId,
                           @RequestParam int roomId,
                           @RequestParam boolean reviewType,
                           @RequestParam String review,
                           Map<String, Object> model) {
        Date d = new Date(System.currentTimeMillis());
        Room tmpRoom = roomRepository.findByRoomId(roomId);
        Reservation tmpReservation = reservationRepository.findByReservationId(reservationId);
        if ((guestRepository.findByRoom_RoomIdAndReservation_StartDateAndReservation_EndDate(roomId, tmpReservation.getStartDate(), tmpReservation.getEndDate())).size() < tmpRoom.getRoomCapacity()) {



            Company tmpCompany = companyRepository.findByCompanyId(companyId);
            Client tmpClient = clientRepository.findByClientId(clientId);

            Guest tmpGuest = new Guest(tmpReservation, tmpCompany, tmpClient, tmpRoom, reviewType, review);
            guestRepository.save(tmpGuest);


        }
        else
        {
            System.out.println("многовато людей - НАДО КАК-ТО ОБ ЭТОМ СООБЩИТЬ НА СТРАНИЦУ...");
        }
        createIterators(model);

        return "/insert/guest/guest";
    }

    //даем только правильные вещи на выбор в списке
    @GetMapping("/insert/guestValidate")
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

        return "/insert/guest/guestValidate";
    }

    @PostMapping("/insert/guestDelete")
    public String deleteGuest(@RequestParam int guestId, Map<String,Object> model){
        Guest g = guestRepository.findByGuestId(guestId);
        guestRepository.delete(g);

        Iterable<Guest> it = guestRepository.findAll();
        model.put("guests", it);

        return "/insert/guest/guestDelete";
    }
}


