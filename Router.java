import java.io.IOException;

public class Router {

    private int limit;
    private boolean [] connection ;
    Semaphore s;

    public Router(int limit) {
        this.limit = limit;
        s = new Semaphore(limit);
        connection = new boolean[limit] ;
        for(int i = 0 ; i < limit ; ++i){
            connection[i] = false ;
        }
        
    }

    

    public synchronized int connect(Device d) throws InterruptedException, IOException {

        for (int i = 0; i < limit; ++i) 
        {
            if (!connection[i]) {
                d.setRouterid(i);
                s.File2("Connection " + d.getrouterID() + ": " + d.getDeviceName() + " occupied");
                
                connection[i] = true;
                break;
            }
        }
        
        return d.getrouterID() ;
    }
    
    public synchronized void disconnect(Device d) {
        connection[d.getrouterID()] = false;
        notify() ;
    }
}

