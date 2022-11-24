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
            if (serverList.get(i).getIsFree()) {
                Server server = serverList.get(i);
                Event newE =  new Serve(this.customer, this.customer.getArrival(), i);
                Server temp = server.takeCus(this.customer);
                Server update = temp.updateTime(this.customer.getArrival());
                ImList<Server> newList = serverList.set(i, update);
                return new Pair(newE, newList);
            }
        }
        for (int i = 0; i < serverList.size(); i++) {
            if (!serverList.get(i).getIsFree() && serverList.get(i).canQueue()) {
                Server server = serverList.get(i);
                //wait till server free
                //occurtime is arrival time
                Event newE = new Wait(this.customer, this.occurTime, i);
                Server temp = serverList.get(i).takeCus(this.customer);
                ImList<Server> newList = serverList.set(i, temp); 
                return new Pair(newE, newList);
            }
        }
        Event newE = new Leave(this.customer, this.customer.getArrival());
        return new Pair(newE, serverList);
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
        return PRIOR;   
    }
}
