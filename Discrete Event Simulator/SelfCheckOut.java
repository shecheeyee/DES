class SelfCheckOut {

    private final ImList<Server> servers;
    private final int qmax;
    private final ImList<Customer> queue;
    private final int serving;
    private final boolean working;

    SelfCheckOut(ImList<Customer> queue, ImList<Server> servers,
            int qmax, int serving, boolean working) {
        this.servers = servers;
        this.qmax = qmax;
        this.serving = serving;
        this.queue = queue;
        this.working = working;
    }

    public boolean working() {
        return this.working;
    }

    public ImList<Server> server() {
        return this.servers;
    }

    public Customer pop() {
        return this.queue.get(0);        
    }

    public SelfCheckOut removeCus() {
        ImList<Customer> newQueue = this.queue.remove(0);
        return new SelfCheckOut(newQueue, this.servers, this.qmax, this.serving, this.working);
    }


    public SelfCheckOut serveCus(Server s, int i) {
        ImList<Server> newServers = this.servers.set(i, s);
        return new SelfCheckOut(this.queue, newServers, this.qmax, this.serving, this.working);
        
    }

    public SelfCheckOut updateQ(Customer customer) {
        ImList<Customer> newQueue = this.queue.add(customer);
        return new SelfCheckOut(newQueue, this.servers, this.qmax, this.serving + 1, this.working);
    }

    public boolean canServe() {
        for (int i = 0; i < this.servers.size(); i++) {
            if (this.servers.get(i).getIsFree()) {
                return true;
            }
        }
        return false;
    }

    public int serveNum() {
        for (int i = 0; i < this.servers.size(); i++) {
            if (this.servers.get(i).getIsFree()) {
                return i;
            }
        }
        return 0;
    }

    public Server getFreeServer() {
        for (int i = 0; i < this.servers.size(); i++) {
            if (this.servers.get(i).getIsFree()) {
                return this.servers.get(i);
            }
        }
        return this.servers.get(0);
    }

    public boolean canQueue() {
        if (this.queue.size() < this.qmax) {
            return true;
        }
        return false;
    }

    public int enqueueNum() {
        int num = 0;
        double minTime = this.servers.get(0).getAvail();
        for (int i = 0; i < this.servers.size(); i++) {
            if (this.servers.get(i).getAvail() < minTime) {
                minTime = this.servers.get(i).getAvail();
                num = i;
            }
        }
        return num;
    }


}

