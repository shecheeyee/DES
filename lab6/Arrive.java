class Arrive implements Event {
    private final Customer customer;
    private final double occurTime;
    private static final int PRIOR = 4;
    private static final boolean NEXT = true;

    Arrive(Customer customer, double occurTime) {
        this.customer = customer;
        this.occurTime = occurTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " +
            this.customer.toString() + " " + "arrives";
    }

    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            if (server.getIsFree()) {
                if (!server.isRest()) {
                    Event newE =  new Serve(this.customer, this.customer.getArrival(), i);
                    Server temp = server.takeCus(this.customer).makeBusy();
                    Server update = temp.updateTime(this.customer.getArrival());
                    ImList<Server> newList = serverList.set(i, update);
                    Pair<Event, ImList<Server>> pr = new Pair<Event, ImList<Server>>(newE, newList);
                    return pr;
                } else {
                    if (server.getAvail() <= this.customer.getArrival()) {
                        Server temp = server.takeCus(this.customer).makeBusy();
                        Server update = temp.updateTime(this.customer.getArrival());
                        Server last = update.endRest();
                        ImList<Server> newList = serverList.set(i, last);
                        Event newE =  new Serve(this.customer, this.customer.getArrival(), i);
                        Pair<Event, ImList<Server>> pr = new Pair<Event, 
                            ImList<Server>>(newE, newList);
                        return pr;
                    }
                }
            }
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
                    Event newE = new Wait(this.customer, time, i);
                    ImList<Server> newList = serverList.set(i, temp); 
                    Pair<Event, ImList<Server>> pr = new Pair<Event, ImList<Server>>(newE, newList);
                    return pr;
                } 
                Event newE = new Wait(this.customer, this.customer.getArrival(), i);
                Server temp = server.takeCus(this.customer);
                ImList<Server> newList = serverList.set(i, temp); 
                Pair<Event, ImList<Server>> pr = new Pair<Event, ImList<Server>>(newE, newList);
                return pr;
            }
        }
        Event newE = new Leave(this.customer, this.customer.getArrival());
        Pair<Event, ImList<Server>> pr = new Pair<Event, ImList<Server>>(newE, serverList);
        return pr;
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
        return new Arrive(this.customer, time);
    }

    public Statistics getStats(Statistics stats) {
        return stats.update(0.0, 0.0, 0);
    }

    public int getQ() {
        return -1;   
    }
}
