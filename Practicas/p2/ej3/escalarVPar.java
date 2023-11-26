//PORTATIL ESTA SOLUCIONADO
/**
 * Esta clase contiene los atributos y metodos de escalarVPar
 * @author Rafael Leal Pardo
 * @version 2022
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class escalarVPar extends Thread{
    private static int vector1[];
    private static int vector2[];
    private static int nVueltas;
    private final int Inicio,Final;

    /**
     * Metodo constructor parametrizado
     * @param in Entero de inicio (indice del vector)
     * @param fn Entero de fin (indice del vector)
     */
    public escalarVPar(int in,int fn){
        this.Inicio = in;
        this.Final = fn;
    }

    /**
     * Metodo que asigna el tamaño a los vectores y les asigna sus valores
     * @param vueltas
     */
    public static void rellenar(int vueltas){
        nVueltas = vueltas;
        vector1 = new int[nVueltas];
        vector2 = new int[nVueltas];

        for(int i=0;i<nVueltas;i++){
            vector1[i] = 1000000;
            vector2[i] = i+1;
        }
    }

    /**
     * Metodo que muestra el producto escalar
     */
    public static void solucion(){
        for(int i=0;i<nVueltas;i++){
            System.out.println(vector1[i]);
        }
    }

    /**
     * Metodo run que teniendo en cuenta los indices inicio y fin se hara el producto ecalar de los vectores
     */
    public void run(){
        for(int i=Inicio;i<Final;i++){
            vector1[i] = vector1[i] * vector2[i];
        }
    }

    /**
     * Metodo main en el que se realizaran las operaciones del producto escalar mediante hilos
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        try(Scanner Scan = new Scanner(System.in)){
            System.out.println("Introduzca el tamanio de los vectores: \n");
            int div,nvuel = Scan.nextInt();
            
            if((float)nvuel/6 > 0.5){
                div = nvuel/6 + 1;
            }else{
                div = nvuel/6;
            
            }
            escalarVPar h1 = new escalarVPar(0,div);
            escalarVPar h2 = new escalarVPar(div ,div * 2);
            escalarVPar h3 = new escalarVPar(div * 2,div * 3);
            escalarVPar h4 = new escalarVPar(div * 3,div * 4);
            escalarVPar h5 = new escalarVPar(div * 4,div * 5);
            escalarVPar h6 = new escalarVPar(div * 5,nvuel);
            

            escalarVPar.rellenar(nvuel);

            h1.start();h2.start();h3.start();h4.start();h5.start();h6.start();
            h1.join();h2.join();h3.join();h4.join();h5.join();h6.join();
            
            //System.out.println(div);
            //System.out.println("La solucion al producto escalar es: ");
            //escalarVPar.solucion();

        }catch (InputMismatchException  ERROR) {
            System.out.println("No se introdujo un entero valido");
        }
    }
}