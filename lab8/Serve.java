class Serve implements Event {
    private final Customer customer;
    private final double occurTime;
    //private final Server server;
    private final int qNum;
    private static final int PRIOR = 2;
    private static final boolean NEXT = true;
    private final int qMax;
    private final boolean remove;

    Serve(Customer customer, double occurTime, int qNum, int qMax, boolean remove) {
        this.customer = customer;
        this.occurTime = occurTime;
        this.qNum = qNum;
        this.qMax = qMax;
        this.remove = remove;
    }

    @Override
    public String toString() {
        String str;
        if (this.qNum < this.qMax) {
            str = "";
        } else {
            str = "self-check ";
        }
        return String.format("%.3f", this.getTime()) + " " +
            this.customer.toString() + " serves by " + str + (this.qNum + 1); 
    }

    public Customer getCus() {
        return this.customer;
    }

    public Pair<Event, Pair<ImList<Server>, SelfCheckOut>> execute(ImList<Server> serverList, 
            SelfCheckOut autoCounter) {
        if (this.qNum < serverList.size()) {
            //next avail time for server is += service time of curr cus
            Server newServer = serverList.get(this.qNum).updateTime(this.getTime() 
                    + this.customer.getService()).makeBusy();
            //occurtime of done will be when server is avail
            Event newE = new Done(this.customer, newServer.getAvail(), this.qNum, this.qMax);
            ImList<Server> newList = serverList;

            newList = serverList.set(this.qNum, newServer.endRest());

            Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                SelfCheckOut>(newList, autoCounter);
            Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
                Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
            return prTwo;
        }
        Server s = autoCounter.server().get(this.qNum - serverList.size())
            .updateTime(this.getTime() + this.customer.getService()).makeBusy();
        //Server s = autoCounter.server().get(this.qNum - serverList.size());
        SelfCheckOut newAutoCounter = autoCounter.serveCus(s, this.qNum - serverList.size());
        SelfCheckOut finalCounter = newAutoCounter;
        if (this.remove) { //remove the scheduled serve
            finalCounter = newAutoCounter.removeCus();
        }
        Event newE = new Done(this.customer, s.getAvail(), this.qNum, this.qMax);
        Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
            SelfCheckOut>(serverList, finalCounter);
        Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
            Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);

        return prTwo;        

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
        return new Serve(this.customer, time, this.qNum, this.qMax, this.remove);
    }

    public Event changeQ(int num) {
        return new Serve(this.customer, this.occurTime, num, this.qMax, this.remove);
    }
}

