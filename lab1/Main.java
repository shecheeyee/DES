import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int cusNumber = 1;
        double totalTime = 0.0;
        Server S = new Server(name);
       

        while (sc.hasNextDouble()) {    
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble();
            Customer C = new Customer(arrivalTime, serviceTime);
            if ( arrivalTime <  totalTime ) {
                System.out.println("customer " + cusNumber + C.toString());
            }
            else {
                totalTime = C.totalTime();
                System.out.println("customer " + cusNumber + S.toString());
            }
            
            cusNumber++;
                    
        }
    sc.close();     
    }
    
        
}


