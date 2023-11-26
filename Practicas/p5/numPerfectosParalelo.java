import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Esta clase contiene los atributos y metodos de numPerfectosParalelo
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class numPerfectosParalelo{
    private int NumLimite = 2000;

    /**
     * Metodo Constructor
     */
    public numPerfectosParalelo(){}

    /**
     * Metodo main de la clase que realizará el pool de threads que usará a la clase ContarNumPerfectos
     * la cual realiza una implementación de Callable<Integer>
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        numPerfectosParalelo nPerfectos = new numPerfectosParalelo();

        int nPuntos = nPerfectos.NumLimite;
        int nTareas = Runtime.getRuntime().availableProcessors();
        int tVentana = nPuntos/nTareas;
        int ini = 1;
        int fin = tVentana;
        int totalPerfectos = 0;
        
        ArrayList<Future<Integer>> Contador = new ArrayList<Future<Integer>>();

        try (ThreadPoolExecutor ept = new ThreadPoolExecutor(nTareas, nTareas,0L,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>())) {
            long InicioCrono = System.currentTimeMillis();
            
            for(int i=0; i<nTareas; i++){
                Contador.add((Future<Integer>) ept.submit(new ContarNumPerfectos(ini,fin)));
                ini = fin + 1;
                fin += tVentana; 
            }

            for(Future<Integer> iterador : Contador){
                try{
                    totalPerfectos += iterador.get();
                }catch(CancellationException CANCEL_ERROR){System.out.println("Ha ocurrido un fallo");}
                 catch(ExecutionException EXECUTION_ERROR){System.out.println("Ha ocurrido un fallo");}
                 catch(InterruptedException INTERRUPTED_ERROR){System.out.println("Ha ocurrido un fallo");}
            }
            ept.shutdown();
            long FinalizoCrono = System.currentTimeMillis(); 

            System.out.println("El total de numero perfectos es de: "+ totalPerfectos);
            System.out.println((FinalizoCrono - InicioCrono) + " ms");
            System.out.println("SpeedUp: " + (long)7/(FinalizoCrono - InicioCrono));
        }catch(Error ERROR){
            System.out.println("Ha ocurrido un fallo");
        }        
    }
}



/**
 * Esta clase contiene los atributos y metodos de ContarNumPerfectos
 * @author Rafael Leal Pardo
 * @version 2022
*/

class ContarNumPerfectos implements Callable<Integer>{
    private final int Inicio,FIN;
    private int contador;

    /**
     * Metodo Constructor parametrizado
     * @param ini Entero de inicio
     * @param fin Entero de fin
     */
    public ContarNumPerfectos(int ini,int fin){
        contador = 0;
        Inicio = ini;
        FIN = fin;
    }

    /**
     * Metodo call que devolvera un integer que es la cantidad de numeros que son perfectos
     * en el intervalo Inicio-FIN.
     * @return Devuelve el numero de perfectos
     */
    public Integer call(){
        int aux = 0;

        for(int i=Inicio; i<FIN; i++){
            aux = 0;
            for(int j=1; j<i; j++){
                if(i % j == 0){
                    aux += j;
                }
            }
            if(i == aux){
                contador++;
            }
        }
        return contador;
    }
}