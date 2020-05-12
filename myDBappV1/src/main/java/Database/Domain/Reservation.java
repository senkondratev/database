package Database.Domain;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buildingId")
    private Building building;

    private Date startDate;
    private Date endDate;

    public Reservation() {
    }

    public Reservation(Building b, Date d1, Date d2) {
        building = b;
        startDate = d1;
        endDate = d2;
    }
    //группа геттеров для "красивого вывода" - геттеры "чужих" полей
    public int getBuildingId(){
        return building.getBuildingId();
    }
    public int getBuildingLevel(){
        return building.getLevel();
    }

    //геттеры полей этого класса
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
}
