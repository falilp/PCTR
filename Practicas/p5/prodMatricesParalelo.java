import java.util.Random;
import java.util.concurrent.*;

/**
 * Esta clase contiene los atributos y metodos de prodMatricesParalelo
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class prodMatricesParalelo{
    private final int tamanio = 1000;
    private int[][] matriz1;
    private int[][] matriz2;
    private int[][] resultado;

    /**
     * Metodo constructor que inicializa las matrices
     */
    public prodMatricesParalelo(){
        matriz1 = new int[tamanio][tamanio];
        matriz2 = new int[tamanio][tamanio];
        resultado = new int[tamanio][tamanio];
        Random ran = new Random();

        for(int i=0; i<tamanio; i++){
            for(int j=0; j<tamanio; j++){
                matriz1[i][j] = ran.nextInt();
                matriz2[i][j] = ran.nextInt();
            }
        }
    }

    /**
     * Metodo main de la clase que creará un pool de threads y se ejecutaran para llevar a cabo 
     * las operaciones que se le asignan.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        prodMatricesParalelo product = new prodMatricesParalelo();

        int nPuntos = product.tamanio;
        int nTareas = Runtime.getRuntime().availableProcessors();
        int tVentana = nPuntos/nTareas;
        int ini = 0;
        int fin = tVentana;

        try (ThreadPoolExecutor ept = new ThreadPoolExecutor(nTareas, nTareas,0L,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>())) {
            ept.prestartAllCoreThreads();
            long InicioCrono = System.currentTimeMillis();
            
            for(int i=0; i<nTareas; i++){
                ept.execute(new ProductoMatrices(ini,fin,product.tamanio,product.matriz1,product.matriz2,product.resultado));
                ini = fin + 1;
                fin += tVentana; 
            }
            ept.shutdown();

            boolean vBool = false;
  		    while(!vBool){
                try{ 
                    vBool = ept.awaitTermination(20, TimeUnit.SECONDS); 
                }catch( InterruptedException ERROR){
                    System.out.println("Ha ocurrido un fallo");
                }
            }

            long FinalizoCrono = System.currentTimeMillis();           
            System.out.println((FinalizoCrono - InicioCrono) + " ms");

            System.out.println("SpeedUp: " + (long)10/(FinalizoCrono - InicioCrono));
        }catch(Error ERROR){
            System.out.println("Ha ocurrido un fallo");
        }        
    }
}

/**
 * Esta clase contiene los atributos y metodos de prodMatricesParalelo
 * @author Rafael Leal Pardo
 * @version 2022
*/
class ProductoMatrices implements Runnable{
    private final int Inicio,FIN;
    private static int nTamanio;
    private int[][] matriz1;
    private int[][] matriz2;
    private static int[][] resultado;

    /**
     * Metodo constructor parametrizado
     * @param ini Entero de inicio del thread
     * @param fin Entero de fin del thread
     * @param tam Entero del tamañio total de filas y columnas 
     * @param m1  Matriz numero 1 
     * @param m2  Matriz numero 2 
     * @param res Matriz donde se guardará el resultado
     */
    public ProductoMatrices(int ini,int fin,int tam, int[][] m1, int[][] m2, int[][] res){
        this.Inicio = ini;
        this.FIN = fin;
        this.nTamanio = tam;
        this.matriz1 = m1;
        this.matriz2 = m2;
        this.resultado = res;
    }

    /**
     * Metodo que realizara el producto de matrices y 
     * sera asignado en la matriz *resultado* 
     * (los valores son aquellos que le corresponde a cada thread)
     */
    public void run(){
        for(int i=Inicio; i<FIN; i++){
            for(int j=0; j<nTamanio; j++){
                resultado[i][j] += (matriz1[i][j] * matriz2[j][i]);
            }
        }
    }

    /**
     * Metodo que imprimira el vector *resultado* por pantalla
     */
    public static void mostrar(){
        System.out.print("[");

        for(int i=0; i<nTamanio; i++){
            for(int j=0; j<nTamanio; j++){
                System.out.print(" "+ resultado[i][j] +" ");
            }
        }

        System.out.print("]\n");
    }

}
