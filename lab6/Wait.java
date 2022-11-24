class Wait implements Event {
    private final Customer customer;
    private final double occurTime;
    private final int qNum;
    private static final int PRIOR = 3;
    private static final boolean NEXT = true;

    Wait(Customer customer, double occurTime, int qNum) {
        this.customer = customer;
        this.occurTime = occurTime;
        this.qNum = qNum;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + this.customer.toString()
            + " waits at " + (this.qNum + 1);
    }

    public Customer getCus() {
        return this.customer;
    }

    public String getEvent() {
        return "Wait";
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
        Event newE;
        Server server = serverList.get(this.qNum);
        if (server.getAvail() < this.occurTime) {
            newE = new Serve(this.customer, this.occurTime, this.qNum);
        } else {
            newE = new Serve(this.customer, server.getAvail(), this.qNum);
        }
        Pair<Event, ImList<Server>> pr = new Pair<Event, ImList<Server>>(newE, serverList);
        return pr;
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
        return stats.update(0.0, 0.0, 0);
    }

    public int getQ() {
        return this.qNum;   
    }

    public Event update(double time) {
        return new Wait(this.customer, time, this.qNum);
    }
}
