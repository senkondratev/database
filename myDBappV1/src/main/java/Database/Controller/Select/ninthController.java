package Database.Controller.Select;

import Database.Domain.Guest;
import Database.Domain.Room;
import Database.Repository.GuestRepository;
import Database.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ninthController {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    private float getTotalProfit(List<Room> itRoom, Date d1, Date d2){
        float profit = 0;
        for (Room r: itRoom){
            Map<Integer, Integer> isCounted = new HashMap<>();
            List<Guest> guestList = guestRepository.findByRoom_RoomIdAndReservation_StartDateAfterAndReservation_EndDateBefore(r.getRoomId(), d1, d2);
            System.out.println(r.getRoomId());
            System.out.println(guestList.size());
            System.out.println("----------------------------");
            if(guestList.size()>0){
                for (Guest g: guestList){
                    if(!isCounted.containsKey(g.getReservationId())) {
                        Date start = g.getStartDate();
                        Date end = g.getEndDate();
                        profit += r.getRoomProfit() * ((end.getTime() - start.getTime()) / 86400000 + 1);
                        isCounted.put(g.getReservationId(), 1);
                    }
                }
            }
        }
        return profit;
    }

    @GetMapping("/select/ninth")
    public String ninth(Map<String, Object> model){
        model.put("profit", "Параметры еще не выбраны");
        return "/select/ninth/ninth";
    }

    @GetMapping("/select/ninthSelect")
    public String ninthSelect(@RequestParam Date d1,
                               @RequestParam Date d2,
                               @RequestParam(required = false) Integer capacity,
                               @RequestParam(required = false) Integer floor,
                               @RequestParam(required = false) Integer profit,
                               @RequestParam(required = false) Integer buildingId,
                               Map<String, Object> model) {
        List<Room> rooms = new ArrayList<>();
        if((capacity!=null)&&(floor==null)&&(profit==null)&&(buildingId==null)){
            rooms = roomRepository.findByRoomCapacity(capacity);
        }
        if((capacity!=null)&&(floor==null)&&(profit==null)&&(buildingId!=null)){
            rooms = roomRepository.findByRoomCapacityAndBuilding_BuildingId(capacity,buildingId);
        }
        if((capacity!=null)&&(floor==null)&&(profit!=null)&&(buildingId==null)){
            rooms = roomRepository.findByRoomCapacityAndRoomProfit(capacity,profit);
        }
        if((capacity!=null)&&(floor==null)&&(profit!=null)&&(buildingId!=null)){
            rooms = roomRepository.findByRoomCapacityAndRoomProfitAndBuilding_BuildingId(capacity, profit,buildingId);
        }
        if((capacity!=null)&&(floor!=null)&&(profit==null)&&(buildingId==null)){
            rooms = roomRepository.findByRoomCapacityAndRoomFloor(capacity, floor);
        }
        if((capacity!=null)&&(floor!=null)&&(profit==null)&&(buildingId!=null)){
            rooms = roomRepository.findByRoomCapacityAndRoomFloorAndBuilding_BuildingId(capacity, floor, buildingId);
        }
        if((capacity!=null)&&(floor!=null)&&(profit!=null)&&(buildingId==null)){
            rooms = roomRepository.findByRoomCapacityAndRoomFloorAndRoomProfit(capacity, floor, profit);
        }
        if((capacity!=null)&&(floor!=null)&&(profit!=null)&&(buildingId!=null)){
            rooms = roomRepository.findByRoomCapacityAndRoomFloorAndRoomProfitAndBuilding_BuildingId(capacity,floor,profit,buildingId);
        }

        if((capacity==null)&&(floor!=null)&&(profit==null)&&(buildingId==null)){
            rooms = roomRepository.findByRoomFloor(floor);
        }
        if((capacity==null)&&(floor!=null)&&(profit==null)&&(buildingId!=null)){
            rooms = roomRepository.findByRoomFloorAndBuilding_BuildingId(floor, buildingId);
        }
        if((capacity==null)&&(floor!=null)&&(profit!=null)&&(buildingId==null)){
            rooms = roomRepository.findByRoomFloorAndRoomProfit(floor,profit);
        }
        if((capacity==null)&&(floor!=null)&&(profit!=null)&&(buildingId!=null)){
            rooms = roomRepository.findByRoomFloorAndBuilding_BuildingIdAndRoomProfit(floor,buildingId,profit);
        }
        if((capacity==null)&&(floor==null)&&(profit==null)&&(buildingId==null)){
            rooms = (List<Room>)roomRepository.findAll();
        }
        if((capacity==null)&&(floor==null)&&(profit==null)&&(buildingId!=null)){
            rooms = roomRepository.findByBuilding_BuildingId(buildingId);
        }
        if((capacity==null)&&(floor==null)&&(profit!=null)&&(buildingId==null)){
            rooms = roomRepository.findByRoomProfit(profit);
        }
        if((capacity==null)&&(floor==null)&&(profit!=null)&&(buildingId!=null)){
            rooms = roomRepository.findByRoomProfitAndBuilding_BuildingId(profit, buildingId);
        }
        model.put("profit", getTotalProfit(rooms,d1,d2));
        return "/select/ninth/ninth";
    }

    @GetMapping("/select/ninthAddCapacity")
    public String addCapacity(){
        return "/select/ninth/addCapacity";
    }
    @GetMapping("/select/ninthRemoveCapacity")
    public String removeCapacity(){
        return "/select/ninth/removeCapacity";
    }

    @GetMapping("/select/ninthAddFloor")
    public String addFloor(){
        return "/select/ninth/addFloor";
    }
    @GetMapping("/select/ninthRemoveFloor")
    public String removeFloor(){
        return "/select/ninth/removeFloor";
    }

    @GetMapping("/select/ninthAddBuilding")
    public String addBuilding(){
        return "/select/ninth/addBuilding";
    }
    @GetMapping("/select/ninthRemoveBuilding")
    public String removeBuilding(){
        return "/select/ninth/removeBuilding";
    }

    @GetMapping("/select/ninthAddProfit")
    public String addProfit(){
        return "/select/ninth/addProfit";
    }
    @GetMapping("/select/ninthRemoveProfit")
    public String removeProfit(){
        return "/select/ninth/removeProfit";
    }
}
