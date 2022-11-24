class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoaders;
    private final int serviceTime;
    private static final int HUNDRED = 100;
    private static final int MIN = 60;

    Cruise(String identifier, int arrivalTime, int numOfLoaders, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoaders = numOfLoaders;
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    public int getArrivalTime() {
        int hours = this.arrivalTime / HUNDRED;
        int min = this.arrivalTime - (hours * HUNDRED);
        return (hours) * MIN + min;
    }

    public int getNumOfLoadersRequired() {
        return this.numOfLoaders;
    }


    @Override
    public String toString() {
        return this.identifier + "@" + String.format("%04d", this.arrivalTime);
    }
}
