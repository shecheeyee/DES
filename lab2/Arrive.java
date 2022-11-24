class Arrive implements Event {
    private final Server server;
    private final Customer newCustomer;
    private final int cusNumber;

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
            return serve.execute();
        } else {
            Leave leave = new Leave(this.newCustomer, this.server, this.cusNumber);
            return leave.execute();
        }
    }

    @Override
    public String toString() {
        return "customer " + cusNumber +  " arrives";
    }
}

