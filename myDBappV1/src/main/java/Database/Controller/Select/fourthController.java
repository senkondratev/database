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
import java.util.List;
import java.util.Map;

@Controller
public class fourthController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private GuestRepository guestRepository;

    private List<Room> getFreeRooms(List<Room> itRoom){

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
            if(flag == 0){
                roomList.add(r);
            }
            flag = 0;
        }
        return roomList;
    }

    @GetMapping("/select/fourth")
    public String fourth(Map<String, Object> model){
        List<Room> itRoom = (List<Room>)roomRepository.findAll();
        List<Room> roomList = getFreeRooms(itRoom);
        model.put("whatisit", "выведены все свободные комнаты");
        model.put("rooms", roomList);
        model.put("count", roomList.size());
        return "/select/fourth/fourth";
    }

    @GetMapping("/select/fourthSelect")
    public String fourthSelect(@RequestParam(required = false) Integer capacity,
                               @RequestParam(required = false) Integer floor,
                               @RequestParam(required = false) Integer profit,
                               @RequestParam(required = false) Integer buildingId,
                               Map<String, Object> model){
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


        List<Room> roomList = getFreeRooms(rooms);
        model.put("whatisit", "выведены комнаты с выбранными ограничениями");
        model.put("rooms", roomList);
        model.put("count", roomList.size());
        return "/select/fourth/fourth";
    }

    @GetMapping("/select/fourthAddCapacity")
    public String addCapacity(){
        return "/select/fourth/addCapacity";
    }
    @GetMapping("/select/fourthRemoveCapacity")
    public String removeCapacity(){
        return "/select/fourth/removeCapacity";
    }

    @GetMapping("/select/fourthAddFloor")
    public String addFloor(){
        return "/select/fourth/addFloor";
    }
    @GetMapping("/select/fourthRemoveFloor")
    public String removeFloor(){
        return "/select/fourth/removeFloor";
    }

    @GetMapping("/select/fourthAddBuilding")
    public String addBuilding(){
        return "/select/fourth/addBuilding";
    }
    @GetMapping("/select/fourthRemoveBuilding")
    public String removeBuilding(){
        return "/select/fourth/removeBuilding";
    }

    @GetMapping("/select/fourthAddProfit")
    public String addProfit(){
        return "/select/fourth/addProfit";
    }
    @GetMapping("/select/fourthRemoveProfit")
    public String removeProfit(){
        return "/select/fourth/removeProfit";
    }
}
