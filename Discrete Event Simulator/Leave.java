class Leave implements Event {
    private final Customer customer;
    private final double occurTime;
    //private final ImList<Server> serverList;
    private static final int PRIOR = 5;
    private static final boolean NEXT = false;

    Leave(Customer customer, double occurTime) {
        this.customer = customer;
        this.occurTime = occurTime;
        //this.serverList = serverList;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) +
            " " + this.customer.toString() + " " + "leaves";
    }

    public Customer getCus() {
        return this.customer;
    }

    public Pair<Event, Pair<ImList<Server>, SelfCheckOut>> execute(ImList<Server> serverList, 
            SelfCheckOut autoCounter) {
        Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
            SelfCheckOut>(serverList, autoCounter);
        Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
            Pair<ImList<Server>, SelfCheckOut>>(this, prOne);
        return prTwo;
    }

    public String getEvent() {
        return "Leave";
    }

    public int getPriorityInt() {
        return PRIOR;
    }

    public double getTime() {
        return this.occurTime;
    }

    public boolean hasNext() {
        return NEXT;
    }

    public Statistics getStats(Statistics stats) {
        return stats.update(0.0, 0.0, 1);
    }

    public int getQ() {
        return 0;   
    }

    public Event update(double time) {
        return new Leave(this.customer, time);
    }

    public Event changeQ(int num) {
        return this;
    }
}


