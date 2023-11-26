import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase contiene los atributos y metodos de prodMatricesSecuencial
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class prodMatricesSecuencial{
    private final int TAM;
    private int[][] matriz1;
    private int[][] matriz2;
    private int[][] resultado;

    /**
     * Metodo constructor parametrizado
     * @param m Valor que se le asignara a las filas y columnas
     */
    public prodMatricesSecuencial(int m){
        TAM = m;


        matriz1 = new int[TAM][TAM];
        matriz2 = new int[TAM][TAM];
        resultado = new int[TAM][TAM];
        Random ran = new Random();

        for(int i=0; i<TAM; i++){
            for(int j=0; j<TAM; j++){
                matriz1[i][j] = ran.nextInt();
                matriz2[i][j] = ran.nextInt();
            }
        }
    }

    /**
     * Metodo que realizara el producto entre dos matrices y 
     * sera asignado en la matriz *resultado*
     */
    public void MatrizXvector(){
        for(int i=0; i<TAM; i++){
            for(int j=0; j<TAM; j++){
                resultado[i][j] += (matriz1[i][j] * matriz2[j][i]);
            }
        }
    }

    /**
     * Metodo que imprimira el vector *resultado* por pantalla
     */
    public void mostrar(){
        System.out.print("[");

        for(int i=0; i<TAM; i++){
            for(int j=0; j<TAM; j++){
                System.out.print(" "+ resultado[i][j] +" ");
            }
            System.out.print("]\n");
        }

    }

    /**
     * Metodo main donde se creara el objeto para realizar las llamadas,operaciones y 
     * se cronometra los tiempos.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        try(Scanner Scan = new Scanner(System.in)){
            System.out.println("Introduce el numero de filas y columnas");
            int tam = Scan.nextInt();
            prodMatricesSecuencial prueba = new prodMatricesSecuencial(tam);

            long InicioCrono = System.currentTimeMillis();

            prueba.MatrizXvector();

            long FinalizoCrono = System.currentTimeMillis();

            //prueba.mostrar();
            System.out.println((FinalizoCrono - InicioCrono) + " ms");
        }catch(InputMismatchException ERROR){
            System.out.println("No se introdujo un entero valido");
        }
    }
}