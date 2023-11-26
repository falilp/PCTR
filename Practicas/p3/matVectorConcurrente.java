import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos de matVectorConcurrente
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class matVectorConcurrente implements Runnable{
    private final int Inicio,FIN;
    private static int nHebras = 2;                    //Cambiar el numero de hilos aqui
    private static int Tamanio = 1000;
    private static int[][] matriz;
    private static int[] vector;
    private static int[] resultado;

    /**
     * Metodo constructor parametrizado
     * @param ini Valor que se le asignara de inicio
     * @param fin Valor que se le asignara de fin
     */
    public matVectorConcurrente(int ini,int fin){
        this.Inicio = ini;
        this.FIN = fin;
    }

    /**
     * Metodo que inicializa los vectores static y les asigna valores Random
     */
    public static void Inicializar(){
        matriz = new int[Tamanio][Tamanio];
        vector = new int[Tamanio];
        resultado = new int[Tamanio];
        Random ran = new Random();

        for(int i=0; i<Tamanio; i++){
            vector[i] = ran.nextInt();
            for(int j=0; j<Tamanio; j++){
                matriz[i][j] = ran.nextInt();
            }
        }
    }

    /**
     * Metodo que realizara el producto y sumatorio de la matriz por el vector y 
     * sera asignado en el vector *resultado* 
     * (los valores son aquellos que le corresponde a cada thread)
     */
    public void run(){
        for(int i=Inicio; i<FIN; i++){
            for(int j=Inicio; j<FIN; j++){
                resultado[i] += (matriz[i][j] * vector[j]);
            }
        }
    }

    /**
     * Metodo que imprimira el vector *resultado* por pantalla
     */
    public static void mostrar(){
        System.out.println("[");
        for(int i=0; i<Tamanio; i++){
            System.out.println(" "+ resultado[i] +" ");
        }
        System.out.println("]");
    }

    /**
     * Metodo main donde se crearan un vector de runnable y otro de threads con el numero 
     * de nHebras que tiene la clase que se le asignara a cada objeto
     * de la clase con sus respectivos valores y se realizaran las llamadas y 
     * se cronometra los tiempos
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        int div = Tamanio/nHebras;
        int resto = Tamanio%nHebras;
        Runnable VecRunnable[] = new Runnable[nHebras];
        Thread VecThread[] = new Thread[nHebras];

        for(int i=0;i<nHebras;i++){
            if(i == nHebras-1){
                VecRunnable[i] = new matVectorConcurrente(div*i,(div*(i+1))+resto);
            }else{
                VecRunnable[i] = new matVectorConcurrente(div*i,div*(i+1));
            }
        }
        for(int i=0;i<nHebras;i++){VecThread[i] = new Thread(VecRunnable[i]);}
        Inicializar();
            
        long InicioCrono = System.currentTimeMillis();
            
        for(int i=0;i<nHebras;i++){VecThread[i].start();}
        for(int i=0;i<nHebras;i++){VecThread[i].join();}

        long FinalizoCrono = System.currentTimeMillis();
        
        mostrar();
        System.out.println((FinalizoCrono - InicioCrono) + " ms");        
    }
}
