class Server {
    private final ImList<Customer> customers;
    private final int qmax;
    private final int qNum;
    private final boolean isFree;
    private final double availTime;

    Server(ImList<Customer> customers, int qmax, int qNum, boolean isFree, double availTime) {
        this.customers = customers;
        this.qmax = qmax;
        this.qNum = qNum;
        this.isFree = isFree;
        this.availTime = availTime;
    }

    @Override
    public String toString() {
        return "server";
    }

    public Server updateAvail(double time) {
        return new Server(this.customers, this.qmax, this.qNum, this.isFree, this.availTime + time);
    }

    public Server updateTime(double time) {
        return new Server(this.customers, this.qmax, this.qNum, this.isFree, time);
    }

    public boolean canQueue() { 
        if (this.customers.size()  >= this.qmax + 1) {
            return false;
        }
        return true;
    }

    public Server takeCus(Customer customer) {
        ImList<Customer> newCustomer = this.customers.add(customer);
        return new Server(newCustomer, this.qmax, this.qNum, this.isFree, this.availTime);
    }

    public Server updateCustomer(int i, Customer newCustomer) {
        ImList<Customer> newCustomerList = this.customers;
        newCustomerList = this.customers.set(i, newCustomer);
        return new Server(newCustomerList, this.qmax, this.qNum, this.isFree, this.availTime);
    }

    public boolean getIsFree() {
        return this.isFree;
    }

    public ImList<Customer> getE() {
        return this.customers;
    }

    public Server updateList(ImList<Customer> list) {
        return new Server(list, this.qmax, this.qNum, this.isFree, this.availTime);
    }

    public Server removeCus() {
        ImList<Customer> newCustomer = this.customers.remove(0);
        return new Server(newCustomer, this.qmax, this.qNum, true, this.availTime);
    }

    public Server makeBusy() {
        return new Server(this.customers, this.qmax, this.qNum, false, this.availTime);
    }

    public Server makeFree() {
        return new Server(this.customers, this.qmax, this.qNum, true, this.availTime);
    }

    public double getAvail() {
        return this.availTime;
    }

}


