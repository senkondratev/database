package Database.Domain;

import javax.persistence.*;

@Entity
public class Building {
    //ВОЗМОЖНО стоит добавить этажность и прочее(смотри старую схема)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buildingId;

    private int level;

    public Building() {
    }

    public Building(int lvl) {
        level = lvl;
    }

    //геттеры полей этого класса
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
