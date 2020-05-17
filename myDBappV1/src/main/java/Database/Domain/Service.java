package Database.Domain;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buildingId")
    private Building building;

    private int servicePrice;

    private String serviceName;

    public Service() {
    }

    public Service(Building b, int p, String s) {
        building = b;
        servicePrice = p;
        serviceName = s;
    }

    //группа геттеров для "красивого вывода" - геттеры "чужих" полей
    public int getBuildingId(){
        return building.getBuildingId();
    }
    public int getBuildingLevel(){
        return building.getLevel();
    }


    //геттеры полей этого класса
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
