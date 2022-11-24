import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int cusNumber = 1;
        double totalTime = 0.0;
        Customer zero = new Customer(0.0, 0.0);
        Server server = new Server(name, totalTime, zero);

        while (sc.hasNextDouble()) {    
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble();
            Customer newCustomer = new Customer(arrivalTime, serviceTime);
            //if ( server.getFinal() > customer.getArrival()) {
            //    System.out.println("customer " + cusNumber + customer.toString());
            //}
            //replace by
            Arrive arrive = new Arrive(newCustomer, server, cusNumber);
            System.out.println(arrive.toString());
            System.out.println(arrive.execute());
            if (server.getFinal() <= newCustomer.getArrival()) {
                server = server.takeCus(newCustomer);
            
            }
            //else {
            //    server = server.takeCus(customer);
            //    server.updateTime();
            //    System.out.println("customer " + cusNumber + server.toString());
            //}
            
            cusNumber++;
                    
        }
        sc.close();     
    }
    
        
}


