package Database.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guestId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservationId")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roomId")
    private Room room;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "guestId")
    private List<Receipt> receipts;

    private boolean reviewType;

    private String guestReview;

    public Guest() {
    }

    public Guest(Reservation res, Company com, Client cli, Room roo, boolean rt, String review) {
        reservation = res;
        company = com;
        client = cli;
        room = roo;
        reviewType = rt;
        guestReview = review;
    }

    //группа геттеров для "красивого вывода" - геттеры "чужих" полей
    public int getReservationId(){
        return reservation.getReservationId();
    }
    public String getCompanyName(){
        return company.getCompanyName();
    }
    public String getClientSurname(){
        return client.getClientSurname();
    }
    public int getRoomId(){
        return room.getRoomId();
    }
    public int getBuildingId()
    {
        return reservation.getBuildingId();
    }

    //геттеры полей этого класса
    public String getGuestReview() {
        return guestReview;
    }

    public void setGuestReview(String guestReview) {
        this.guestReview = guestReview;
    }

    public boolean isReviewType() {
        return reviewType;
    }

    public void setReviewType(boolean reviewType) {
        this.reviewType = reviewType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
}
