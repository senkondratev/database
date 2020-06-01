package Database.Domain;

import javax.persistence.*;

@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receiptId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guestId")
    private Guest guest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceId")
    private Service service;

    public Receipt() {
    }

    public Receipt(Guest g, Service s) {
        guest = g;
        service = s;
    }

    //группа геттеров для "красивого вывода" - геттеры "чужих" полей
    public String getServiceName(){
        return service.getServiceName();
    }
    public int getGuestId(){
        return guest.getGuestId();
    }
    public int getServiceId(){return service.getServiceId();}
    public int getServicePrice(){
        return  service.getServicePrice();
    }
    public int getBuildingId(){return service.getBuildingId();}
    public int getBuildingLevel(){return service.getBuildingLevel();}


    //геттеры полей этого класса
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }
}
