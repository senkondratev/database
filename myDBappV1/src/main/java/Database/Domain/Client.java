package Database.Domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "clientId")
    private List<Guest> guests;

    private String clientName;
    private String clientSurname;
    private Date firstAppearance;



    public Client() {
    }

    public Client(String name, String surname, Date date){
        clientName = name;
        clientSurname = surname;
        firstAppearance = date;
    }

    //геттеры полей этого класса
    public Date getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(Date firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getClientID() {
        return clientId;
    }

    public void setClientID(int clientID) {
        this.clientId = clientID;
    }
}
