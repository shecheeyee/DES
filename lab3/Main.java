import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int cusNumber = 1;
        double totalTime = 0.0;
        Customer zero = new Customer(0.0, 0.0);
        Server server = new Server(name, totalTime, zero);
        PQ<Event> pq = new PQ<>(new EventCompare()); 
        Pair<Event, PQ<Event>> pair = pq.poll();
                 
        while (sc.hasNextDouble()) {    
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble();
            Customer newCustomer = new Customer(arrivalTime, serviceTime);
            Event nextEvent = new Arrive(newCustomer, server, cusNumber);
            pq = pq.add(nextEvent);
            while (nextEvent.hasNextEvent()) {
                
                nextEvent = nextEvent.execute();

                pq = pq.add(nextEvent); //add next event into queue
              
            }
            if (server.getFinal() <= newCustomer.getArrival()) { //update the timings
                server = server.takeCus(newCustomer);
            }
            cusNumber++;
        }
        while (!pq.isEmpty()) {
            Pair<Event, PQ<Event>> newPair = pq.poll();
            Event pop = newPair.first();
            pq = newPair.second();
            System.out.println(pop.toString());
        }
        sc.close();
    }
}
            
