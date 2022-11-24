class Done implements Event {
    private final Customer customer;
    private final double occurTime;
    //private final Server server;
    private final int qNum;
    private static final int PRIOR = 1;
    private static final boolean NEXT = false;

    Done(Customer customer, double occurTime, int qNum) {
        this.customer = customer;
        this.occurTime = occurTime;
        //this.server = server;
        this.qNum = qNum;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + this.customer.toString() +
            " done serving by " + (this.qNum + 1);
    }

    public Customer getCus() {
        return this.customer;
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
        ImList<Server> newList = serverList;
        Server newServer = serverList.get(this.qNum);
        Server update = newServer.removeCus().makeFree();
        newList = serverList.set(this.qNum, update);
        return new Pair(this, newList);
    }

    public String getEvent() {
        return "Done";
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
        return stats.update(0.0, 1.0, 0);
    }
    
    public int getQ() {
        return this.qNum;   
    }

    public Event update(double time) {
        return new Done(this.customer, time, this.qNum);
    }
}


