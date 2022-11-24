class Arrive implements Event {
    private final Server server;
    private final Customer newCustomer;
    private final int cusNumber;
    private static final int PRIOR = 2;


    Arrive(Customer newCustomer, Server server, int cusNumber) {
        this.server = server;
        this.newCustomer = newCustomer;
        this.cusNumber = cusNumber;
    }


    
    public Event execute() {
        //System.out.println(this.toString());
        //System.out.println(server.getFinal() );
        //System.out.println(newCustomer.getArrival() );
        if (server.getFinal() <= newCustomer.getArrival()) {
            Serve serve =  new Serve(this.newCustomer, this.server, this.cusNumber);
            //server = server.takeCus(newCustomer);
            return serve;
        } else {
            Leave leave = new Leave(this.newCustomer, this.server, this.cusNumber);
            return leave;
        }
    }

    @Override
    public String toString() {
        return this.getTime() + " customer " + cusNumber +  " arrives";
    }

    public String getEvent() {
        return "Arrive";
    }

    public double getTime() {
        return newCustomer.getArrival();
    }

    public String getPriority() {
        return "a";
    }

    public boolean hasNextEvent() {
        return true;
    }

    public int getCusNumber() {
        return cusNumber;
    }
    
    public int getPriorityInt() {
        return PRIOR;
    }
}
