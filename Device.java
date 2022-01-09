import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Device extends Thread {

    private String name;
    private String type;
    private int routerid;
    Router r ;
    

    Device(String name, String type, Router x) {
        this.name = name;
        this.type = type;
        this.r = x;
        routerid = 0;
    }

    public void setRouterid(int routerid) {
        this.routerid = routerid;
    }

    public int getrouterID() {
        return routerid;
    }
    
    public void File(String x) throws IOException {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter("output.txt", true));
            w.append(x);
            w.newLine();
            w.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Semaphore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void run() {
        try {
            r.s.P(this);
        } catch (IOException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            routerid = r.connect(this) ;
        } catch (InterruptedException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String name = this.name;
        try {
            File("Connection " + this.routerid + ": " + name + " login");
        } catch (IOException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            File("Connection " + this.routerid + ": " + name + " performs online activity");
        } catch (IOException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            File("Connection " + this.routerid + ": " + name + " Logged out");            

        } catch (IOException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        r.disconnect(this);
        r.s.V();
    }

    public String getDeviceName() {
        return name;
    }

    public void setDeviceName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   
}
