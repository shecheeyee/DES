class Serve implements Event {
    private final Customer customer;
    private final double occurTime;
    //private final Server server;
    private final int qNum;
    private static final int PRIOR = 2;
    private static final boolean NEXT = true;

    Serve(Customer customer, double occurTime, int qNum) {
        this.customer = customer;
        this.occurTime = occurTime;
        this.qNum = qNum;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " +
            this.customer.toString() + " serves by " + (this.qNum + 1); 
    }

    public Customer getCus() {
        return this.customer;
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
        //next avail time for server is += service time of curr cus
        Server newServer = serverList.get(this.qNum).updateTime(this.getTime() 
                + this.customer.getService()).makeBusy();
        //occurtime of done will be when server is avail
        Event newE = new Done(this.customer, newServer.getAvail(), this.qNum);
        ImList<Server> newList = serverList;

        newList = serverList.set(this.qNum, newServer);
        return new Pair<Event, ImList<Server>>(newE, newList);
    }

    public String getEvent() {
        return "Serve";
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
        return stats.update(this.getTime() - this.customer.getArrival(), 0.0, 0);
    }
    
    public int getQ() {
        return this.qNum;   
    }

    public Event update(double time) {
        return new Serve(this.customer, time, this.qNum);
    }
}

