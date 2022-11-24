class Arrive implements Event {
    private final Customer customer;
    private final double occurTime;
    private static final int PRIOR = 4;
    private static final boolean NEXT = true;
    private final int qMax;

    Arrive(Customer customer, double occurTime, int qMax) {
        this.customer = customer;
        this.occurTime = occurTime;
        this.qMax = qMax;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " +
            this.customer.toString() + " " + "arrives";
    }

    public Pair<Event, Pair<ImList<Server>, SelfCheckOut>> execute(ImList<Server> serverList, 
            SelfCheckOut autoCounter) {
        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            if (server.getIsFree()) {
                if (!server.isRest()) {
                    Event newE =  new Serve(this.customer, this.customer.getArrival(),
                            i, this.qMax, false);
                    Server temp = server.takeCus(this.customer).makeBusy();
                    Server update = temp.updateTime(this.customer.getArrival());
                    ImList<Server> newList = serverList.set(i, update);
                    Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                        SelfCheckOut>(newList, autoCounter);
                    Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event, 
                        Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
                    return prTwo;

                } else {
                    if (server.getAvail() <= this.customer.getArrival()) {
                        Server temp = server.takeCus(this.customer).makeBusy();
                        Server update = temp.updateTime(this.customer.getArrival());
                        Server last = update.endRest();
                        ImList<Server> newList = serverList.set(i, last);
                        Event newE =  new Serve(this.customer, this.customer.getArrival(),
                                i, this.qMax, false);
                        Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                            SelfCheckOut>(newList, autoCounter);
                        Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
                            Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
                        return prTwo;
                    }
                }
            } 
        }
        if (autoCounter.canServe() && autoCounter.server().size() != 0) {
            int serverNum = autoCounter.serveNum();
            Server temp = autoCounter.getFreeServer().takeCus(this.customer).makeBusy();
            Server update = temp.updateTime(this.customer.getArrival());
            SelfCheckOut newAutoCounter = autoCounter.serveCus(update, serverNum);
            Event newE =  new Serve(this.customer, this.customer.getArrival(),
                    serverNum + serverList.size(), this.qMax, false);
            Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                SelfCheckOut>(serverList, newAutoCounter);
            Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
                Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
            return prTwo;
        }

        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            if (server.canQueue()) {
                //wait till server free occurtime is arrival time
                if (server.isRest()) {
                    Server temp = server.takeCus(this.customer);
                    Double time = this.customer.getArrival();
                    if (this.customer.getArrival() >= server.getAvail()) {
                        //if someone comes at a timing later than server rest
                        //means that server ended rest
                        //consecutively will be directed to condition above
                        temp = temp.endRest();
                    }
                    Event newE = new Wait(this.customer, time, i, this.qMax);
                    ImList<Server> newList = serverList.set(i, temp); 
                    Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                        SelfCheckOut>(newList, autoCounter);
                    Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
                        Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
                    return prTwo;

                } else {
                    Event newE = new Wait(this.customer, this.customer.getArrival(), i, this.qMax);
                    Server temp = server.takeCus(this.customer);
                    ImList<Server> newList = serverList.set(i, temp);
                    Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                        SelfCheckOut>(newList, autoCounter);
                    Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
                        Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
                    return prTwo;
                }
            }
        }
        if (autoCounter.canQueue() && autoCounter.server().size() != 0) {
            SelfCheckOut newAutoCounter = autoCounter.updateQ(this.customer);
            Event newE = new Wait(this.customer, this.customer.getArrival(),
                    serverList.size(), this.qMax);
            Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
                SelfCheckOut>(serverList, newAutoCounter);
            Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
                Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
            return prTwo;
        }

        Event newE = new Leave(this.customer, this.customer.getArrival());
        Pair<ImList<Server>, SelfCheckOut> prOne = new Pair<ImList<Server>,
            SelfCheckOut>(serverList, autoCounter);
        Pair<Event, Pair<ImList<Server>, SelfCheckOut>> prTwo = new Pair<Event,
            Pair<ImList<Server>, SelfCheckOut>>(newE, prOne);
        return prTwo;
    }

    public Customer getCus() {
        return this.customer;
    }

    public String getEvent() {
        return "Arrive";
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

    public Event update(double time) {
        return new Arrive(this.customer, time, this.qMax);
    }

    public Event changeQ(int num) {
        return this;
    }

    public Statistics getStats(Statistics stats) {
        return stats.update(0.0, 0.0, 0);
    }

    public int getQ() {
        return -1;   
    }
}
