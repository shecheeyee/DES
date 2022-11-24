import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int qmax;
    private final ImList<Pair<Double, Supplier<Double>>> inputTimes;
    private final ImList<Server> serverList;
    private final Supplier<Double> restTime;

    Simulator(int numOfServers, int qmax, ImList<Pair<Double, Supplier<Double>>> inputTimes,
            Supplier<Double> restTime) {
        this.numOfServers = numOfServers;
        this.qmax = qmax;
        this.inputTimes  = inputTimes;
        ImList<Server> serverList = new ImList<Server>();
        this.serverList = serverList;
        this.restTime = restTime;
    }

    public String simulate() {
        PQ<Event> pq = new PQ<>(new EventCompare());
        Statistics stats = new Statistics(0.0, 0.0, 0);
        ImList<Server> newServerList = new ImList<Server>();
        int cusId = 1;
        for (int i = 0; i < this.numOfServers; i++) {
            newServerList = newServerList.add(new Server(new ImList<Customer>(),
                        this.qmax, i, true, 0.0, false));
        }
        for (int i = 0; i < inputTimes.size(); i++) {
            Pair<Double, Lazy<Double>> times = new Pair<Double, Lazy<Double>>(
                    inputTimes.get(i).first(), Lazy.<Double>of(inputTimes.get(i).second()));
            Customer customer = new Customer(times, cusId);
            Event event = new Arrive(customer, customer.getArrival());
            pq = pq.add(event);
            cusId++;
        }

        while (!pq.isEmpty()) {
            
            Pair<Event, PQ<Event>> newPair = pq.poll();
            pq = newPair.second();
            Event pop = newPair.first();
            Pair<Event, ImList<Server>> evServer;
            stats = pop.getStats(stats);
            //System.out.print(newServerList);
            if (pop.hasNext()) {
                System.out.println(pop.toString());
                evServer = pop.execute(newServerList);
                Event executed = evServer.first();
                pq = pq.add(executed);
                if (executed.getEvent() == "Done") {
                    Pair<Event, PQ<Event>> newPair2 = pq.poll();
                    pq = newPair2.second();
                    Event pop2 = newPair2.first();
                    while (pop2.getEvent() == "Serve" && pop2.getQ() == pop.getQ() && 
                            pop2.getTime() == pop.getTime()) {
                        pop2 = pop2.update(executed.getTime());
                        pq = pq.add(pop2);
                        newPair2 = pq.poll();
                        pop2 = newPair2.first();
                        pq = newPair2.second();
                    }
                    
                    pq = pq.add(pop2);
                    
                }
            } else {
                System.out.println(pop.toString());
                if (pop.getEvent() == "Done") {
                    Double rTime = restTime.get();
                    if (rTime > 0.0) {
                        Server restServer = newServerList.get(pop.getQ());
                        Server updateServer = restServer.updateAvail(rTime).makeRest();
                        newServerList = newServerList.set(pop.getQ(), updateServer);
                        PQ<Event> pq2 = new PQ<>(new EventCompare());
                        while (!pq.isEmpty()) {
                            Pair<Event, PQ<Event>> newPair3 = pq.poll();
                            Event e = newPair3.first();
                            pq = newPair3.second();
                            if (e.getQ() == pop.getQ()) { //if same q update time for events
                                pq2 = pq2.add(e.update(e.getTime() + rTime));
                            } else {
                                pq2 = pq2.add(e);
                            }
                        }
                        pq = pq2;
                    }
                }
                evServer = pop.execute(newServerList);
            }
            newServerList = evServer.second();
        }
        return stats.toString();
    }
}






