import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int qmax;
    private final ImList<Pair<Double, Supplier<Double>>> inputTimes;
    private final ImList<Server> serverList;
    private final Supplier<Double> restTime;
    private final int numOfSelfChecks;

    Simulator(int numOfServers, int numOfSelfChecks, 
            int qmax, ImList<Pair<Double, Supplier<Double>>> inputTimes,
            Supplier<Double> restTime) {
        this.numOfServers = numOfServers;
        this.qmax = qmax;
        this.inputTimes  = inputTimes;
        ImList<Server> serverList = new ImList<Server>();
        this.serverList = serverList;
        this.restTime = restTime;
        this.numOfSelfChecks = numOfSelfChecks;
    }

    public String simulate() {
        PQ<Event> pq = new PQ<>(new EventCompare());
        Statistics stats = new Statistics(0.0, 0.0, 0);
        ImList<Server> newServerList = new ImList<Server>();
        ImList<Server> autoServers = new ImList<Server>();
        int cusId = 1;
        for (int i = 0; i < this.numOfServers; i++) {
            newServerList = newServerList.add(new Server(new ImList<Customer>(),
                        this.qmax, i, true, 0.0, false, false));
        }
        for (int i = 0; i < this.numOfSelfChecks; i++) {
            autoServers = autoServers.add(new Robo(new ImList<Customer>(),
                        1, i, true, 0.0, false, true));
        }

        SelfCheckOut autoCounter = new SelfCheckOut(new ImList<Customer>(),
                autoServers, this.qmax, 0, true);

        for (int i = 0; i < inputTimes.size(); i++) {
            Pair<Double, Lazy<Double>> times = new Pair<Double, 
                Lazy<Double>>(inputTimes.get(i).first(),
                        Lazy.<Double>of(inputTimes.get(i).second()));
            Customer customer = new Customer(times, cusId);
            Event event = new Arrive(customer, customer.getArrival(), this.numOfServers);
            pq = pq.add(event);
            cusId++;
        }
        
        while (!pq.isEmpty()) {
            //System.out.println(newServerList.get(0).getE());
            //System.out.println("is resting: " + newServerList.get(0).isRest());
            Pair<Event, PQ<Event>> newPair = pq.poll();
            pq = newPair.second();
            Event pop = newPair.first();
            Pair<Event, Pair<ImList<Server>, SelfCheckOut>> evServer;
            stats = pop.getStats(stats);
            if (pop.hasNext()) {
                System.out.println(pop.toString());
                evServer = pop.execute(newServerList, autoCounter);
                Event executed = evServer.first();
                pq = pq.add(executed);
                if (executed.getEvent() == "Done" && executed.getQ() < this.numOfServers) {
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
                } else if (executed.getEvent() == "Done" && executed.getQ() >= this.numOfServers) {
                    Pair<Event, PQ<Event>> newPair3 = pq.poll();
                    pq = newPair3.second();
                    Event pop3 = newPair3.first();

                    while (pop3.getEvent() == "Serve" && pop3.getTime() == pop.getTime()) {
                        //double lastestTime = executed.getTime();
                        int enQNum = evServer.second().second().enqueueNum();
                        pop3 = pop3.update(evServer.second().second()
                                .server().get(enQNum).getAvail());
                        pop3 = pop3.changeQ(enQNum + this.numOfServers);
                        pq = pq.add(pop3);
                        newPair3 = pq.poll();
                        pop3 = newPair3.first();
                        pq = newPair3.second();
                    }
                    pq = pq.add(pop3);
                }
                newServerList = evServer.second().first();
                autoCounter = evServer.second().second();

            } else {
                System.out.println(pop.toString());
                if (pop.getEvent() == "Done" && pop.getQ() < this.numOfServers) {
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
                evServer = pop.execute(newServerList, autoCounter);
                newServerList = evServer.second().first();
                autoCounter = evServer.second().second();
            }
        }
        return stats.toString();
    }
}






