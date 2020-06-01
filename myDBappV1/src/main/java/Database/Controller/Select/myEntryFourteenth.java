package Database.Controller.Select;

import java.sql.Date;

public class myEntryFourteenth {
    private int roomId;
    private Date startDate;
    private Date endDate;

    public myEntryFourteenth(int id, Date d1, Date d2){
        roomId = id;
        startDate = d1;
        endDate = d2;
    }
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
