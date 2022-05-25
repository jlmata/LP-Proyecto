import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {
    int numProductor;
    int pte;
    Buffer buffer; // almacen del buffer

    String productor;
    
    //Rangos para Scheme y resultado
    int rangoN = 0; //num dado en el Spinner de valor n
    int rangoM = 0; //num dado en el Spiner de valor m
    String operator; //Para los operadores +,-,*,/
    String scheme; //String para formato de scheme

    String numeroN; //String de variables dadas
    String numeroM;


    //variable para saber el numero de productor
    Producer(int i, int pte, Buffer buffer, int rangoN, int rangoM) {
        //instanciamos el numero de productor
        this.numProductor = i;
        this.pte = pte;
        this.buffer = buffer;// instanciamos el buffer
        //Instanciamos para Scheme el rango
        this.rangoN = rangoN;
        this.rangoM = rangoM;

    }
    
    @Override
    // codigo que se injecta a un nuevo hilo de procesamiento
    public void run() {
        System.out.println("Running Producer...");
        String products = "AEIOU"; //vocales
        char product;
        Random r = new Random(System.currentTimeMillis()); 

        Random randomOperadores = new Random(); //Random para los operadores 
        
        //pendiente de crear bandera
        while(!this.isInterrupted()) {
            
            /*
            //generamos una vocal aleatoria y se la asignamos al producto
            product = products.charAt(r.nextInt(5));
            //String
            productor = "Producer " + numProductor + "produced: " + product;
            //producto se lo enviamos al buffer 
            this.buffer.produce(productor);
            
            //Prueba de terminal
            System.out.println("Producer " + numProductor + " produced: " + product);
            /* Buffer.print("Producer" + numProductor + "produced: " + product); */


            /*Operacion de scheme*/
            
            //Del rango dado, tomar numeros aleatorios dentro de este
            ThreadLocalRandom tlr = ThreadLocalRandom.current();
            int randomN = tlr.nextInt(rangoN, rangoM + 1);
            int randomM = tlr.nextInt(rangoN, rangoM + 1);

            
            //Operadores aleatorios
            switch (randomOperadores.nextInt(4)){  
                
                case 0: operator = "+";
                        break;
                
                case 1: operator = "-";
                        break;
                
                case 2: operator = "*";
                        break;
                
                case 3: operator = "/";
                        break;
                
                default: operator = "?";
            }

            //String
            scheme = '(' + operator + ' ' + randomN + ' ' + randomM + ')';
            //Mandar al buffer
            this.buffer.produce(scheme);

            //System.out.print("( " + operator + " " + numN + " " + numM + " )\n");
            //System.out.print(resultado + "\n");
            
            //Prueba de funcionamiento
            System.out.print("( " + operator + " " + randomN + " " + randomM + " )\n");
            System.out.println("Producer " + numProductor + " produced: " + scheme);
            
            try {
                // esperamos un segundo
                Thread.sleep(pte);//pte
            } catch (InterruptedException ex) {
                // si ocurre un error, lo registramos
                /* Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex); */
                break;
            }
        }
        System.out.println("Producer " + numProductor + " finished");

    }
    // funcion setStop para detener el hilo
  
    
}
