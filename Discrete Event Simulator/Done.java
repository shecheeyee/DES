class Done implements Event {
    private final Customer customer;
    private final double occurTime;
    //private final Server server;
    private final int qNum;
    private static final int PRIOR = 1;
    private static final boolean NEXT = false;
    private final int qMax;

    Done(Customer customer, double occurTime, int qNum, int qMax) {
        this.customer = customer;
        this.occurTime = occurTime;
        //this.server = server;
        this.qNum = qNum;
        this.qMax = qMax;
    }

    @Override
    public String toString() {
        String str;
        if (this.qNum < this.qMax) {
            str = "";
        } else {
            str = "self-check ";
        }
        return String.format("%.3f", this.getTime()) + " " + this.customer.toString() +
            " done serving by " + str +  (this.qNum + 1);
    }

    public Customer getCus() {
        return this.customer;
    }

    public Pair<Event, Pair<ImList<Server>, SelfCheckOut>> execute(ImList<Server> serverList, 
            SelfCheckOut autoCounter) {
        if (this.qNum < serverList.size()) {
            ImList<Server> newList = serverList;
            Server newServer = serverList.get(this.qNum);
            Server update = newServer.makeFree().removeCus();
            newList = serverList.set(this.qNum, update);
            Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                SelfCheckOut>(newList, autoCounter);
            Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
                Pair<ImList<Server>, SelfCheckOut>>(this, prOne);
            return prTwo;
        } else {
            Server s = autoCounter.server().get(this.qNum - serverList.size()).clear(); //remove
            SelfCheckOut newAutoCounter = autoCounter.serveCus(s, this.qNum - serverList.size());
            Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                SelfCheckOut>(serverList, newAutoCounter);
            Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
                Pair<ImList<Server>, SelfCheckOut>>(this, prOne);
            return prTwo;
        }
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
        return new Done(this.customer, time, this.qNum, this.qMax);
    }

    public Event changeQ(int num) {
        return this;
    }
}


