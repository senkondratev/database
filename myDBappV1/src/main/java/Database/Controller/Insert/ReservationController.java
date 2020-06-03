package Database.Controller.Insert;

import Database.Domain.Building;
import Database.Domain.Reservation;
import Database.Repository.BuildingRepository;
import Database.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Map;

@Controller
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    public void createIterators(Map<String, Object> model, String s){
        Iterable<Reservation> itReservation = reservationRepository.findAll();
        model.put("reservations", itReservation);

        Iterable<Building> itBuilding = buildingRepository.findAll();
        model.put("buildings", itBuilding);

        model.put("status", s);
    }

    @GetMapping("/insert/reservation")
    public String reservation(Map<String, Object> model){
        createIterators(model,"Ошибок нет");
        return "/insert/reservation/reservation";
    }

    @PostMapping("/insert/reservation")
    public String addReservation(@RequestParam int buildingId,
                                 @RequestParam Date startDate,
                                 @RequestParam Date endDate,
                                 Map<String, Object> model){
        if(startDate.before(endDate)) {
            Building tmpBuilding = buildingRepository.findByBuildingId(buildingId);
            Reservation tmpReservation = new Reservation(tmpBuilding, startDate, endDate);
            reservationRepository.save(tmpReservation);
            createIterators(model,"Ошибок нет");
        }
        else {
            createIterators(model,"Вы перепутали даты");
        }
        return "/insert/reservation/reservation";
    }

    @PostMapping("/insert/reservationDelete")
    public String deleteReservation(@RequestParam int reservationId, Map<String, Object> model){
        System.out.println("im here");
        Reservation r = reservationRepository.findByReservationId(reservationId);
        reservationRepository.delete(r);

        Iterable<Reservation> it = reservationRepository.findAll();
        model.put("reservations",it);

        return "/insert/reservation/reservationDelete";
    }
}
