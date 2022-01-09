
import java.util.ArrayList;
import java.util.Scanner;

public class Network {

    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Device> devices = new ArrayList<Device>();
    public static int N;
    public static int TC;
    public static RouterGUI graphics ;

    public static void main(String[] args) throws InterruptedException {

        int choice = 0;

        System.out.println("1. For displaying in console\n2. for displaying on GUI");
        choice = input.nextInt();

        if (choice == 1) {
            System.out.println("What is number of WI-FI Connections?");
            N = input.nextInt();

            System.out.println("What is number of devices clients want to connect?");
            TC = input.nextInt();

            Router r = new Router(N);

            for (int i = 0; i < TC; ++i) {
                String name = "";
                String type = "";

                name = input.next();
                type = input.next();
                devices.add(new Device(name, type, r));
            }

            for (int i = 0; i < devices.size(); ++i) {
                devices.get(i).start();
            }
            
            
        }else if(choice == 2){
            graphics = new RouterGUI() ;
        }

    }

}
