import java.util.concurrent.*;
import java.net.*;
import java.io.*;

/**
 * Esta clase contiene los atributos y metodos de ServidorHiloconPool con Runnable
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class ServidorHiloconPool implements Runnable{
    Socket enchufe;
    private static final int nPeticiones = 10;

    /**
     * Metodo constructor parametrizado.
     * @param s Socket que le asignara a la clase
     */
    public ServidorHiloconPool(Socket s){
        enchufe = s;
    }    

    /**
     * Metodo run donde se realizara las escrituras de los datos que son pasados por 
     * los clientes a los hilos, ademas de el cierre de estos clientes.
     */
    public void run(){
        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            
            String datos = entrada.readLine();
            int data = Integer.valueOf(datos).intValue();
        
            PrintWriter salida  = new PrintWriter(new BufferedWriter(new OutputStreamWriter(enchufe.getOutputStream())));
            salida.println(data);
            salida.flush();

            for(int i=1; i<=nPeticiones; i++){
                System.out.println("El hilo "+ Thread.currentThread() +" escribiendo el dato "+ data);
                Thread.sleep(1000);
            }

            enchufe.close();
            System.out.println("El hilo "+ Thread.currentThread() +" cierra su conexion...");

        }catch(Exception ERROR){System.out.println("Error de conexión.");}
    }

    /**
     * Metodo main donde se realizara el pool de thread que se le asignara las escuchas de los clientes
     * y seran ejecurados.
     * @param args
     */
    public static void main (String[] args){
        int tamanio = 1000;
        int puerto = 2001;
        Socket cable;

        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);

            while(true){
                ThreadPoolExecutor tpe = new ThreadPoolExecutor(tamanio,tamanio,0L,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>());
                tpe.prestartAllCoreThreads();

                System.out.println("Esperando solicitud de conexion...");
                for(int index=0; index<tamanio; index++){
                    cable = chuff.accept();
                    tpe.execute(new ServidorHiloconPool(cable));
                }
                System.out.println("Recibida solicitud de conexion...");
                tpe.shutdown();
                
                boolean tempo = false;
                while(!tempo){
                    try{
                        tempo = tpe.awaitTermination(20,TimeUnit.MILLISECONDS);
                    }catch(InterruptedException Except){System.out.println("exception interrupt...");}
                }
            }

        }catch(Exception e){System.out.println("Error en sockets...");}
    }
}