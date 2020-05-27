package Database.Controller.Select;

import Database.Domain.Guest;
import Database.Repository.GuestRepository;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class secondController {
    @Autowired
    private GuestRepository guestRepository;

    private void putter(List<Guest> l, String s, Map<String, Object> model){
        model.put("guests", l);
        model.put("status", s);
        model.put("count", l.size());
    }

    @GetMapping("/select/second")
    public String second(Map<String, Object> model){
        List<Guest> itGuest = (List<Guest>) guestRepository.findAll();
        putter(itGuest, "все хорошо", model);
        return "/select/second/second";
    }

    @GetMapping("/select/secondSelect")
    public String secondSelect(@RequestParam Date d1,
                               @RequestParam Date d2,
                               @RequestParam(required = false) Integer capacity,
                               @RequestParam(required = false) Integer floor,
                               @RequestParam(required = false) Integer profit,
                               @RequestParam(required = false) Integer buildingId,
                               Map<String, Object> model) {
        if (d1.before(d2)) {
            List<Guest> itGuest = new ArrayList<>();
            if ((capacity == null) && (floor == null) && (profit == null) && (buildingId == null)) {
                itGuest = guestRepository.findByReservation_StartDateAfterAndReservation_EndDateBefore(d1, d2);
            }
            //к сожалению, пока тут комбинаторный взрыв. Мб заменить на java reflection?
            if((capacity==null)&&(floor==null)&&(profit==null)&&(buildingId!=null)){
                itGuest = guestRepository.findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBefore(buildingId, d1, d2);

            }
            if((capacity==null)&&(floor==null)&&(profit!=null)&&(buildingId!=null)){
                itGuest = guestRepository.findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfit(buildingId, d1,d2,profit);
            }
            if((capacity==null)&&(floor!=null)&&(profit==null)&&(buildingId!=null)){
                itGuest = guestRepository.findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomFloor(buildingId, d1, d2, floor);
            }
            if((capacity==null)&&(floor!=null)&&(profit!=null)&&(buildingId!=null)){
                itGuest = guestRepository.findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomFloorAndRoom_RoomProfit(buildingId,d1,d2,floor,profit);
            }
            if((capacity!=null)&&(floor==null)&&(profit==null)&&(buildingId!=null)){
                itGuest = guestRepository.findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacity(buildingId, d1, d2, capacity);
            }
            if((capacity!=null)&&(floor==null)&&(profit!=null)&&(buildingId!=null)){
                itGuest = guestRepository.findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacityAndRoom_RoomProfit(buildingId, d1, d2, capacity, profit);
            }
            if((capacity!=null)&&(floor!=null)&&(profit==null)&&(buildingId!=null)){
                itGuest = guestRepository.findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacityAndRoom_RoomFloor(buildingId, d1,d2,capacity, floor);
            }
            if((capacity!=null)&&(floor!=null)&&(profit!=null)&&(buildingId!=null)){
                itGuest = guestRepository.findByReservation_Building_BuildingIdAndReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacityAndRoom_RoomFloorAndRoom_RoomProfit(buildingId, d1,d2,capacity,floor, profit);
            }
            if((capacity==null)&&(floor==null)&&(profit!=null)&&(buildingId==null)){
                itGuest = guestRepository.findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfit(d1,d2,profit);
            }
            if((capacity==null)&&(floor!=null)&&(profit!=null)&&(buildingId==null)){
                itGuest = guestRepository.findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfitAndRoom_RoomFloor(d1, d2, profit, floor);
            }
            if((capacity!=null)&&(floor==null)&&(profit!=null)&&(buildingId==null)){
                itGuest = guestRepository.findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfitAndRoom_RoomCapacity(d1, d2, profit, capacity);
            }
            if((capacity!=null)&&(floor!=null)&&(profit!=null)&&(buildingId==null)){
                itGuest = guestRepository.findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomProfitAndRoom_RoomCapacityAndRoom_RoomFloor(d1, d2, profit, capacity, floor);
            }
            if((capacity==null)&&(floor!=null)&&(profit==null)&&(buildingId==null)){
                itGuest = guestRepository.findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomFloor(d1, d2, floor);
            }
            if((capacity!=null)&&(floor==null)&&(profit==null)&&(buildingId==null)){
                itGuest = guestRepository.findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomCapacity(d1, d2, capacity);
            }
            if((capacity!=null)&&(floor!=null)&&(profit==null)&&(buildingId==null)){
                itGuest = guestRepository.findByReservation_StartDateAfterAndReservation_EndDateBeforeAndRoom_RoomFloorAndRoom_RoomCapacity(d1,d2,floor,capacity);
            }

            putter(itGuest,"все хорошо", model);
        }
        else{
            List<Guest> itGuest = (List<Guest>) guestRepository.findAll();
            putter(itGuest, "вы перепутали даты", model);
        }
        return "/select/second/second";
    }

    @GetMapping("/select/secondAddCapacity")
    public String addCapacity(){
        return "/select/second/addCapacity";
    }
    @GetMapping("/select/secondRemoveCapacity")
    public String removeCapacity(){
        return "/select/second/removeCapacity";
    }

    @GetMapping("/select/secondAddFloor")
    public String addFloor(){
        return "/select/second/addFloor";
    }
    @GetMapping("/select/secondRemoveFloor")
    public String removeFloor(){
        return "/select/second/removeFloor";
    }

    @GetMapping("/select/secondAddBuilding")
    public String addBuilding(){
        return "/select/second/addBuilding";
    }
    @GetMapping("/select/secondRemoveBuilding")
    public String removeBuilding(){
        return "/select/second/removeBuilding";
    }

    @GetMapping("/select/secondAddProfit")
    public String addProfit(){
        return "/select/second/addProfit";
    }
    @GetMapping("/select/secondRemoveProfit")
    public String removeProfit(){
        return "/select/second/removeProfit";
    }
}
