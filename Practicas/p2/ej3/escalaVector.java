import java.util.*;
/**
 * Esta clase contiene los atributos y metodos de tareaRunnable
 * @author Rafael Leal Pardo
 * @version 2022
*/
public class escalaVector{

    private int[] vector1; //Supondre que este vector estara cargado con valores de 10^6
    private int[] vector2; //Supondre que este vector estara cargado de valores desde 1 hasta el tamañio introducido
    private int tamanio;

    /**
     * Metodo constructor parametrizado
     * @param tam Tamaño de los vectores
     */
    public escalaVector(int tam){
        this.tamanio = tam;
        this.vector1 = new int[tamanio];
        this.vector2 = new int[tamanio];

        for(int i=0;i<tamanio;i++){
            vector1[i] = 1000000;
            vector2[i] = i+1;
        }
    }

    /**
     * Metodo que realiza el metodo del producto escalar
     * @return resultado de realizar el producto escalar
     */
    public void productoEscalar(){
        for(int i=0;i<tamanio;i++){
            vector1[i] = vector1[i] * vector2[i];
            //System.out.println(vector1[i]);
        }
    }

    /**
     * Metodo main donde se realiza el programa
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        try(Scanner Scan = new Scanner(System.in)){
            System.out.println("Introduzca el tamanio de los vectores: \n");
            int tam = Scan.nextInt();
            escalaVector vectores = new escalaVector(tam);

            //System.out.println("La solucion al producto escalar es: ");
            vectores.productoEscalar();

        }catch(InputMismatchException ERROR){
            System.out.println("No se introdujo un entero valido");
        }
    }
}   