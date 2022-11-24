class Server {
    private final ImList<Customer> customers;
    private final int qmax;
    private final int qNum;
    private final boolean isFree;
    private final double availTime;
    private final boolean rest;
    private final boolean selfCheck;

    Server(ImList<Customer> customers, int qmax, int qNum, boolean isFree,
            double availTime, boolean rest, boolean selfCheck) {
        this.customers = customers;
        this.qmax = qmax;
        this.qNum = qNum;
        this.isFree = isFree;
        this.availTime = availTime;
        this.rest = rest;
        this.selfCheck = selfCheck;
    }

    @Override
    public String toString() {
        return "server" + this.qNum + " avail time is " + this.availTime;
    }

    public boolean isRest() {
        return this.rest;
    }

    public Server updateAvail(double time) {
        return new Server(this.customers, this.qmax, this.qNum, this.isFree,
                this.availTime + time, this.rest, this.selfCheck);
    }

    public Server updateTime(double time) {
        return new Server(this.customers, this.qmax, this.qNum,
                this.isFree, time, this.rest, this.selfCheck);
    }

    public boolean canQueue() {
        if (!this.rest) { 
            if (this.customers.size()  >= this.qmax + 1) {
                return false;
            }
            return true;
        } else {
            if (this.customers.size() >= this.qmax) {
                return false;
            }
            return true;
        }
    }

    public Server takeCus(Customer customer) {
        ImList<Customer> newCustomer = this.customers.add(customer);
        return new Server(newCustomer, this.qmax, this.qNum, this.isFree,
                this.availTime, this.rest, this.selfCheck);
    }

    public Server updateCustomer(int i, Customer newCustomer) {
        ImList<Customer> newCustomerList = this.customers;
        newCustomerList = this.customers.set(i, newCustomer);
        return new Server(newCustomerList, this.qmax, this.qNum, this.isFree,
                this.availTime, this.rest, this.selfCheck);
    }

    public boolean getIsFree() {
        return this.isFree;
    }

    public ImList<Customer> getE() {
        return this.customers;
    }

    public Server updateList(ImList<Customer> list) {
        return new Server(list, this.qmax, this.qNum, this.isFree,
                this.availTime, this.rest, this.selfCheck);
    }

    public Server removeCus() {
        ImList<Customer> newCustomer = this.customers.remove(0);
        return new Server(newCustomer, this.qmax, this.qNum, true, 
                this.availTime, this.rest, this.selfCheck);
    }

    public Server makeBusy() {
        return new Server(this.customers, this.qmax, this.qNum, false,
                this.availTime, this.rest, this.selfCheck);
    }

    public Server makeFree() {
        return new Server(this.customers, this.qmax, this.qNum, true,
                this.availTime, this.rest, this.selfCheck);
    }

    public double getAvail() {
        return this.availTime;
    }

    public Server makeRest() {
        return new Server(this.customers, this.qmax, this.qNum, this.isFree, 
                this.availTime, true, this.selfCheck);
    }

    public Server endRest() {
        return new Server(this.customers, this.qmax, this.qNum, this.isFree,
                this.availTime, false, this.selfCheck);
    }

    public int getQ() {
        return this.qNum;
    }

    public Server clear() {
        return new Server(new ImList<Customer>(), this.qmax, this.qNum, true,
                this.availTime, this.rest, this.selfCheck);
    }
}


