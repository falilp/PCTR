/**
 * Esta clase contiene los atributos y metodos de una Uso_hebra
 * @author Rafael Leal Pardo
 * @version 2022
 * @see Persona
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class Uso_hebra extends hebra{
    /**
     * Metodo constructor de Uso_hebra parametrizado
     * @param nVueltas Numero de vueltas a realizar (iteraciones del for)
     * @param tipoHilo Acción que realizara la hebra
     * Llama a super para crear objeto de clase hebra (llama a su constructor)
     */
    public Uso_hebra(int nVueltas, Accion tipoHilo) {
        super(nVueltas, tipoHilo);
    }    

    /**
     * Metodo main que realizara el incremento y el decremento mediante threads de la clase hebra
     * @param args
     * @throws Exception
    */public static void main(String [] args) throws Exception{
        try(Scanner scan = new Scanner(System.in)){
            System.out.println("Introduzca el numero de veces a conntar:\n");
            int nVuel = scan.nextInt();
            
            hebra p = new Uso_hebra (nVuel ,Accion.INCREMENTO);
            hebra q = new Uso_hebra (nVuel ,Accion.DECREMENTO);

            p.start();
            q.start();
            p.join();
            q.join();
            
            System.out.println("El contador n: " + observador());
        
        }catch(InputMismatchException ERROR){
            System.out.println("No se introdujo un entero valido");
        }
    }
}
