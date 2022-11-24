class Wait implements Event {
    private final Customer customer;
    private final double occurTime;
    private final int qNum;
    private static final int PRIOR = 3;
    private static final boolean NEXT = true;
    private final int qMax;

    Wait(Customer customer, double occurTime, int qNum, int qMax) {
        this.customer = customer;
        this.occurTime = occurTime;
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
        return String.format("%.3f", this.getTime()) + " " + this.customer.toString()
            + " waits at " + str +  (this.qNum + 1);
    }

    public Customer getCus() {
        return this.customer;
    }

    public String getEvent() {
        return "Wait";
    }

    public Pair<Event, Pair<ImList<Server>, SelfCheckOut>> execute(ImList<Server> serverList, 
            SelfCheckOut autoCounter) {
        if (this.qNum < serverList.size()) {
            Event newE;
            Server server = serverList.get(this.qNum);
            if (server.getAvail() < this.occurTime) {
                newE = new Serve(this.customer, this.occurTime, this.qNum, this.qMax, true);
            } else {
                newE = new Serve(this.customer, server.getAvail(), this.qNum, this.qMax, true);
            }
            Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                SelfCheckOut>(serverList, autoCounter);
            Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
                Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
            return prTwo;
        }
        int num = autoCounter.enqueueNum(); //queue w soonest time        
        Event newE;
        Server server = autoCounter.server().get(num);
        double time;
        if (server.getAvail() < this.occurTime) {
            newE = new Serve(this.customer, this.occurTime,
                    num + serverList.size(), this.qMax, true);
            //insert into that server at a future time
            //time = this.occurTime;
        } else {
            newE = new Serve(this.customer, server.getAvail(),
                    num + serverList.size(), this.qMax, true);
            //insert into that server at a future time
            //time = server.getAvail();
        }
        Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
            SelfCheckOut>(serverList, autoCounter);
        Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
            Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
        return prTwo;
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
        return new Wait(this.customer, time, this.qNum, this.qMax);
    }
    
    public Event changeQ(int num) {
        return this;
    }
}
