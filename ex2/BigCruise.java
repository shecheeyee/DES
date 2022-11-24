class BigCruise extends Cruise { 
    private static final int PASPERMIN = 50;
    private static final int PERLOADER = 40;

    BigCruise(String identifier, int arrivalTime, int length, int numOfPassengers) {
        super(identifier, arrivalTime, length, numOfPassengers); 
    }

    @Override
    public int getNumOfLoadersRequired() {
        if (super.getNumOfLoadersRequired() % PERLOADER == 0) {
            return super.getNumOfLoadersRequired() / PERLOADER;
        } else {
            return (super.getNumOfLoadersRequired() / PERLOADER) + 1;
        }
    }

    @Override
    public int getServiceTime() {
        if (super.getServiceTime() % PASPERMIN == 0) {
            return super.getServiceTime() / PASPERMIN;
        } else {
            return (super.getServiceTime() / PASPERMIN) + 1;
        }
    }
}
