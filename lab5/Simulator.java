import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int qmax;
    private final ImList<Pair<Double, Supplier<Double>>> inputTimes;
    private final ImList<Server> serverList;

    Simulator(int numOfServers, int qmax, ImList<Pair<Double, Supplier<Double>>> inputTimes) {
        this.numOfServers = numOfServers;
        this.qmax = qmax;
        this.inputTimes  = inputTimes;
        ImList<Server> serverList = new ImList<Server>();
        this.serverList = serverList;
    }

    Simulator(int numOfServers, int qmax, ImList<Pair<Double, Supplier<Double>>> inputTimes, 
            ImList<Server> serverList) {
        this.numOfServers = numOfServers;
        this.qmax = qmax;
        this.inputTimes = inputTimes;
        this.serverList = serverList;
    }


    public String simulate() {
        PQ<Event> pq = new PQ<>(new EventCompare());
        Statistics stats = new Statistics(0.0, 0.0, 0);
        ImList<Server> newServerList = new ImList<Server>();
        Pair<Event, PQ<Event>> pair = pq.poll();
        int cusId = 1;
        for (int i = 0; i < this.numOfServers; i++) {
            newServerList = newServerList.add(new Server(new ImList<Customer>(),
                        this.qmax, i, true, 0.0));
        }
        for (int i = 0; i < inputTimes.size(); i++) {
            Pair<Double, Lazy<Double>> times = new Pair(inputTimes.get(i).first(), 
                    Lazy.<Double>of(inputTimes.get(i).second()));
            Customer customer = new Customer(times, cusId);
            Event event = new Arrive(customer, customer.getArrival());
            pq = pq.add(event);
            cusId++;
        }

        while (!pq.isEmpty()) {
            PQ<Event> pq2 = new PQ<>(new EventCompare());
            Pair<Event, PQ<Event>> newPair = pq.poll();
            pq = newPair.second();
            Event pop = newPair.first();
            Pair<Event, ImList<Server>> evServer;
            stats = pop.getStats(stats);
            if (pop.hasNext()) {
                System.out.println(pop.toString());
                evServer = pop.execute(newServerList);
                Event executed = evServer.first();
                pq = pq.add(executed);
                if (executed.getEvent() == "Done") {
                    Pair<Event, PQ<Event>> newPair2 = pq.poll();
                    pq = newPair2.second();
                    Event pop2 = newPair2.first();
                    while (pop2.getEvent() == "Serve" && pop2.getQ() == pop.getQ() && pop2.getTime() == pop.getTime()) {
                        pop2 = pop2.update(executed.getTime());
                        pq = pq.add(pop2);
                    }
                    if (pop2.getEvent() != "Serve") {
                        pq = pq.add(pop2);
                    }
                }
            } else {
                System.out.println(pop.toString());
                evServer = pop.execute(newServerList);
                
            }
            newServerList = evServer.second();

        }
        return stats.toString();
    }
}

          




