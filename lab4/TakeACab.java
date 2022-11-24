class TakeACab implements Service {
    private static final int FARE = 33;
    private static final int BOOKINGFEE = 200;
    
    public int computeFare(int dist, int numPassengers, int time) {
        return FARE * dist + BOOKINGFEE;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }

    public String getName() {
        return "TakeACab";
    }


}
