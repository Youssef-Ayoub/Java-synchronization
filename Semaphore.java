
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Semaphore {

    protected int value = 0 ;

    public int getValue() {
        return value;
    }
    
    
    protected Semaphore()
    {
        value = 0 ;
    }
    protected Semaphore(int initial)
    {
        value = initial ;
    }
    
    public void File2(String word) throws IOException {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter("output.txt", true));
            w.append(word);
            w.newLine();
            w.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Semaphore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void P(Device d) throws IOException {

        value-- ;
        if (value < 0)
        {
            File2(d.getDeviceName() + " " + d.getType() + " arrived and waiting");
            try {
                wait();
            }
            catch( InterruptedException e ) { }
        }else {
            File2( d.getDeviceName() + " " +  d.getType() + " arrived ");
        }
    }
    public synchronized void V() {
        value++ ;
        if (value <= 0) {
            notify();
        }
    }

}
