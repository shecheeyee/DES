class ShareARide implements Service {
    private static final int FARE = 50;
    private static final int SURCHARGE = 500;  
    private static final int MAXTIME = 900;
    private static final int MINTIME = 600;

    public int computeFare(int dist, int numPassengers, int time) {
        if (time > MAXTIME || time < MINTIME) {
            return (FARE * dist) / numPassengers;
        } else {
            return (FARE * dist + SURCHARGE) / numPassengers;
        }
    }
    
    @Override
    public String toString() {
        return "ShareARide";
    }

    public String getName() {
        return "ShareARide";
    }

}

