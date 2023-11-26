import java.util.Random;
import java.util.concurrent.*;

/**
 * Esta clase contiene los atributos y metodos de resImagenPar
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class resImagenPar{
    private final int tamanio = 1000;
    private int[][] matriz1;
    private int[][] matriz2;

    /**
     * Metodo constructor que inicializa las matrices
     */
    public resImagenPar(){
        matriz1 = new int[tamanio][tamanio];
        matriz2 = new int[tamanio][tamanio];
        Random ran = new Random();

        for(int i=0; i<tamanio; i++){
            for(int j=0; j<tamanio; j++){
                matriz1[i][j] = ran.nextInt()%255;
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
        resImagenPar product = new resImagenPar();

        int nPuntos = product.tamanio;
        int nTareas = 16;//Runtime.getRuntime().availableProcessors();
        int tVentana = nPuntos/nTareas;
        int ini = 0;
        int fin = tVentana;

        try (ThreadPoolExecutor ept = new ThreadPoolExecutor(nTareas, nTareas,0L,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>())) {
            ept.prestartAllCoreThreads();
            long InicioCrono = System.currentTimeMillis();
            
            for(int i=0; i<nTareas; i++){
                ept.execute(new TratamientoImagen(ini,fin,product.tamanio,product.matriz1,product.matriz2));
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

            System.out.println("SpeedUp: " + (long)14/(FinalizoCrono - InicioCrono));
        }catch(Error ERROR){
            System.out.println("Ha ocurrido un fallo");
        }        
    }
}

/**
 * Esta clase contiene los atributos y metodos de TratamientoImagen
 * @author Rafael Leal Pardo
 * @version 2022
*/

class TratamientoImagen implements Runnable{
    private final int Inicio,FIN;
    public static int nTamanio;
    public static int matrizE[][];
    public static int matrizS[][];

    /**
     * Metodo constructor parametrizado
     * @param ini Entero de inicio del thread
     * @param fin Entero de fin del thread
     * @param tam Entero del tamañio total de filas y columnas 
     * @param matriz Matriz numero 1 
     * @param resultad Matriz donde se guardará el resultado
     */
    public TratamientoImagen(int ini,int fin,int tam,int[][] matriz,int[][] resultado){
        this.Inicio = ini;
        this.FIN = fin;
        this.nTamanio = tam;
        this.matrizE = matriz;
        this.matrizS = resultado;
    }

    /**
     * Metodo que realiza el tratamiento de imagenes
     */
    public void run(){
        for(int i=Inicio; i<FIN; i++){
            for(int j=Inicio; j<nTamanio; j++){
                matrizS[i][j] = 4*matrizE[i][j];

                if(i+1 < FIN){matrizS[i][j] -= matrizE[i+1][j];}
				if(j+1 < nTamanio){matrizS[i][j] -= matrizE[i][j+1];}
				if(i-1 >= 0){matrizS[i][j] -= matrizE[i-1][j];} 
				if(j-1 >= 0){matrizS[i][j] -= matrizE[i][j-1];} 
				
				matrizS[i][j] = matrizE[i][j] / 8;
            }
        }
    }
}
