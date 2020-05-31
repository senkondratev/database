package Database.Controller.Select;

public class myEntry {
    private int roomId;
    private float profit;
    public int getRoomId(){
        return roomId;
    }
    public float getProfit(){
        return profit;
    }
    public void setRoomId(int i){
        roomId = i;
    }
    public void setProfit(float f){
        profit = f;
    }
    public myEntry(Integer i , Float f){
        roomId = i;
        profit = f;
    }
}
