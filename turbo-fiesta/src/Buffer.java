
import java.util.logging.Level;
import java.util.logging.Logger;


public class Buffer {
    
    String[] buffer;
    int tamBuffer;
    
    Buffer(int tamBuffer) {
        // inicializamos el buffer en cero
        this.tamBuffer = tamBuffer;
        this.buffer = new String[tamBuffer];
    }
    
    synchronized String consume() {
        String scheme = "";
        
        // si el buffer esta vacio, esperamos        
        while(this.tamBuffer == 0) {
            try {
                wait();
            }
            catch (InterruptedException ex) {
                /* Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex); */
                break;
            }
        }

        for (int i = 0; i < this.tamBuffer; i++){
            if (this.buffer[i] != null) {
                scheme = this.buffer[i];
                this.buffer[i] = null;
                break;
            }
            
        }
        
        return scheme;
    }
    
    synchronized void produce(String scheme) {
        while(this.tamBuffer != 0) {
            try {
                wait();
            } 
            catch (InterruptedException ex) {
                /* Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex); */
                return;
            }
        }
        
        notifyAll();
    }
    
}
