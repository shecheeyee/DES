class Service {
    private final Loader loader;
    private final Cruise cruise;
    private final int timeOfService;
    private static final int MIN = 60;
    private static final int HUNDRED = 100;

    Service(Loader loader, Cruise cruise, int timeOfService) {
        this.loader = loader;
        this.cruise = cruise;
        this.timeOfService = timeOfService;
    }

    @Override
    public String toString() {
        return String.format("%04d", this.serviceTime()) +
            " : " + loader.toString() + " serving " + cruise.toString();
    }

    public int serviceTime() {
        int hours = this.timeOfService / MIN;
        int mins = this.timeOfService % MIN;
        return hours * HUNDRED + mins;
    }
}

