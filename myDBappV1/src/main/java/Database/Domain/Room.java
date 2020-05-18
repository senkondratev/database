package Database.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buildingId")
    private Building building;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval =  true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "roomId")
    private List<Guest> guests;


    private int roomProfit;//прибыль за день аренды.

    private int roomFloor;

    private int roomCapacity;

    public Room() {
    }

    public Room(Building b, int profit, int floor, int capacity) {
        building = b;
        roomProfit = profit;
        roomFloor = floor;
        roomCapacity = capacity;
    }

    //группа геттеров для "красивого вывода" - геттеры "чужих" полей
    public int getBuildingId(){
        return building.getBuildingId();
    }
    public int getBuildingLevel(){
        return building.getLevel();
    }


    //геттеры полей этого класса

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public int getRoomProfit() {
        return roomProfit;
    }

    public void setRoomProfit(int roomProfit) {
        this.roomProfit = roomProfit;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
