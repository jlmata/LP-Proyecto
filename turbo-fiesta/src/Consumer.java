
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    int numConsumidor;
    int cte;
    Buffer buffer;
    
    Consumer(int numConsumidor,int cte, Buffer buffer) {
        // instanciamos el buffer
        this.numConsumidor = numConsumidor;
        this.cte = cte;
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        //char product;

        //Scheme
        String scheme;
        String resultado;

        char operador; //Para poder tomar los operadores dentro de la funcion
        char numN;  //Para poder tomar el valor dentro del String
        char numM;

        float valorN;   //Resultado numerico de la operacion
        float valorM;
        //float resultado;

        
        // cinco ciclos de consumo
        while(!this.isInterrupted()) {
            //product = this.buffer.consume(); //Obtiene productos de la forma (* 3 5) y lo resuelve
            //System.out.println("Consumer " + numConsumidor + " consumed: " + product);
            /* Buffer.print("Consumer consumed: " + product); */

            scheme = this.buffer.consume();
            System.out.println("Scheme: " + scheme);
            //Navegar el string y tomar cada caracter
            //(* 2 4)
            //|||||||
            //0123456
            operador = scheme.charAt(1);
            numN = scheme.charAt(3);
            numM = scheme.charAt(5);

            //Transformar un char en un numero para operaciones
            valorN = Float.parseFloat(String.valueOf(numN));
            valorM = Float.parseFloat(String.valueOf(numM));

            //Por cada operador, hacer la operacion
            switch(operador){

                case '+' : 
                    resultado = String.valueOf(valorN + valorM);
                    //resultado = valorN + valorM;
                    break;

                case '-': 
                    resultado = String.valueOf(valorN - valorM);;
                    //resultado = valorN - valorM;
                    break;

                case '*': 
                    resultado = String.valueOf(valorN * valorM);;
                    //resultado = valorN * valorM;
                    break;

                case '/': 
                    resultado = String.valueOf(valorN/valorM);
                    //resultado = valorN / valorM;;
                    break;

                default: 
                    resultado = String.valueOf(0);
                    //resultado = 0;
                    break;

            }
            
            System.out.print(resultado + "\n");

            
            try {
                // esperamos un segundo
                Thread.sleep(cte);
            } catch (InterruptedException ex) {
                // si ocurre un error, lo registramos
                /* Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex); */
                break;
            }
        }
        System.out.println("Consumer " + numConsumidor + " finished");
    }
    
}
