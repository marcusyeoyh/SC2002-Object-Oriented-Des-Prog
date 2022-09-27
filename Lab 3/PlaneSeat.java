public class PlaneSeat {
    //instance variables
    private int seatId;
    private boolean assigned = false;
    private int customerId;

    //constructor
    public PlaneSeat(int seat_id){
        this.seatId = seat_id;
    }

    //class methods
    public int getSeatID(){
        return seatId;
    }
    public int getCustomerID(){
        return customerId;
    }
    public boolean isOccupied(){
        return assigned;
    }
    public void assign(int cust_id){
        assigned = true;
        customerId = cust_id;
    }
    public void unAssign(){
        assigned = false;
        customerId = 0;
    }
}
