import java.util.*;

/**
 * Esta clase contiene los atributos y metodos de prodEscalar
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class prodEscalar{

    private int[] vector1; //Supondre que este vector estara cargado con valores de 10^6
    private int[] vector2; //Supondre que este vector estara cargado de valores desde 1 hasta el tamañio introducido
    private int[] vectorEscalar;
    private int tamanio;

    /**
     * Metodo constructor parametrizado
     * @param tam Tamaño de los vectores
     */
    public prodEscalar(int tam){
        this.tamanio = tam;
        this.vector1 = new int[tamanio];
        this.vector2 = new int[tamanio];
        this.vectorEscalar = new int[tamanio];

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
            vectorEscalar[i] = vector1[i] * vector2[i];
        }
    }
    
    /**
     * Metodo que retorna el sumatorio del producto escalar
     * @return sumatorio del producto escalar
     */
    public int ResultadoProductoEcalar(){
        int sumatorio = 0;

        for(int i = 0; i<tamanio; i++){
            sumatorio += vectorEscalar[i]; 
        }

        return sumatorio;
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
            prodEscalar vectores = new prodEscalar(tam);
            
            long InicioCrono = System.currentTimeMillis();

            vectores.productoEscalar();
            long FinalizoCrono = System.currentTimeMillis();

            System.out.println((FinalizoCrono - InicioCrono) + " ms");
            System.out.println("La solucion al producto escalar es: "+ vectores.ResultadoProductoEcalar() + " Es el sumatorio");            
        }catch(InputMismatchException ERROR){
            System.out.println("No se introdujo un entero valido");
        }
    }
}   