import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase contiene los atributos y metodos de matVector
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class matVector{
    private final int filas,columnas;
    private int[][] matriz;
    private int[] vector;
    private int[] resultado;

    /**
     * Metodo constructor parametrizado
     * @param fil Valor que se le asignara a las filas
     * @param col Valor que se le asignara a las columnas
     */
    public matVector(int fil,int col){
        filas = fil;
        columnas = col;

        matriz = new int[filas][columnas];
        vector = new int[columnas];
        resultado = new int[filas];
        Random ran = new Random();

        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                matriz[i][j] = ran.nextInt();
               
                vector[j] = ran.nextInt();
            }
        }
    }

    /**
     * Metodo que realizara el producto y sumatorio de la matriz por el vector y 
     * sera asignado en el vector *resultado*
     */
    public void MatrizXvector(){
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                resultado[i] += (matriz[i][j] * vector[j]);
            }
        }
    }

    /**
     * Metodo que imprimira el vector *resultado* por pantalla
     */
    public void mostrar(){
        System.out.println("[");
        for(int i=0; i<filas; i++){
            System.out.println(" "+ resultado[i] +" ");
        }
        System.out.println("]");
    }

    /**
     * Metodo main donde se creara el objeto para realizar las llamadas y 
     * se cronometra los tiempos
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        try(Scanner Scan = new Scanner(System.in)){
            System.out.println("Introduce el numero de filas ");
            int fil = Scan.nextInt();
            System.out.println("Introduce el numero de columnas ");
            int col = Scan.nextInt();
            matVector prueba = new matVector(fil,col);

            long InicioCrono = System.currentTimeMillis();

            prueba.MatrizXvector();

            long FinalizoCrono = System.currentTimeMillis();

            prueba.mostrar();
            System.out.println((FinalizoCrono - InicioCrono) + " ms");
        }catch(InputMismatchException ERROR){
            System.out.println("No se introdujo un entero valido");
        }
    }

}