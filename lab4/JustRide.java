class JustRide implements Service {
    private static final int FARE = 22;
    private static final int SURCHARGE = 500;
    private static final int MAXTIME = 900;
    private static final int MINTIME = 600;

    public int computeFare(int dist, int numPassengers, int time) {
        if (time > MAXTIME || time < MINTIME) {
            return (FARE * dist);
        } else {
            return FARE * dist + SURCHARGE;
        }
    }
    
    @Override
    public String toString() {
        return "JustRide";
    }

    public String getName() {
        return "JustRide";
    }

}

