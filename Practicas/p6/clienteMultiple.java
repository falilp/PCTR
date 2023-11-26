import java.net.*;
import java.io.*;

/**
 * Esta clase contiene los atributos y metodos de clienteMultiple
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class clienteMultiple{

    /**
     * Metodo main que realizara las peticiones multiples al servidor para escribir un dato y cerrar la conexion.
     * @param args
     */
    public static void main(String[] args){
        int data = (int)(Math.random()*10);
        int puerto = 2001;
        int nPeticiones = 10;
    
        try{
            for(int index=0; index<nPeticiones; index++){
                System.out.println("Realizando conexion...");
                Socket cable = new Socket("localhost", puerto);
                System.out.println("Realizada conexion a "+cable);
                PrintWriter salida= new PrintWriter(new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
                
                salida.println(data);
                salida.flush();

                System.out.println("Cerrando conexion...");
                cable.close();
            }
        }catch(Exception e){
            System.out.println("Error en sockets...");
        }
    }
}