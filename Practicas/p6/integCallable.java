import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase contiene los atributos y metodos de integCallable
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class integCallable{
    public final int nVeces = 100;

    /**
     * Metodo constructor vacio.
     */
    public integCallable(){}

    /**
     * Metodo main que iniciara los valores, future para poder utilizar callable y el pool de threads para realizar el metodo monte carlo.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        integCallable Integral = new integCallable();
        int nTareas = Runtime.getRuntime().availableProcessors();
        int tVentana = Integral.nVeces/nTareas;
        int ini = 1;
        int fin = tVentana;
        int contador = 0;

        ArrayList<Future<Integer>> MT = new ArrayList<Future<Integer>>();

        try{
            ThreadPoolExecutor tpe = new ThreadPoolExecutor(nTareas,nTareas,0L,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>());
            long InicioCrono = System.currentTimeMillis();

            for(int index = 0; index<nTareas; index++){
                MT.add((Future<Integer>) tpe.submit(new MonteCarlo(ini,fin)));
                ini = fin+1;
                fin += tVentana;
            }

            for(Future<Integer> iterador : MT){
                try{
                    contador += iterador.get();
                    
                }catch(CancellationException CANCEL_ERROR){System.out.println("Ha ocurrido un fallo");}
            }
            tpe.shutdown();
            long FinalizoCrono = System.currentTimeMillis(); 

            System.out.println("El numero de aciertos es: "+ contador);
            System.out.println((FinalizoCrono - InicioCrono) + " ms");
        }catch(Exception ERROR){
            System.out.println("Ha ocurrido un fallo");
        }
    }
}

/**
 * Esta clase contiene los atributos y metodos de MonteCarlo con Callable<Integer>
 * @author Rafael Leal Pardo
 * @version 2022
*/

class MonteCarlo implements Callable<Integer>{
    private final int INICIO,FIN;
    private double valor;

    /**
     * Metodo constructor parametrizado.
     * @param ini Entero que indicarael inicio al thread.
     * @param fin Entero que indicarael fin al thread.
     */
    public MonteCarlo(int ini,int fin){
        this.INICIO = ini;
        this.FIN = fin;
        Random ran = new Random();
        valor = ran.nextDouble(0,1);
    }
    
    /**
     * Metodo coseno que realizara como dice su nombre el coseno de x.
     * @param x Double que representa un valor entre 0 y 1.
     * @return Retorna el coseno de x.
     */
    public double coseno(double x){
        return Math.cos(x);
    }

    /**
     * Metodo call de Callable<Integer> que retorna el numero de veces que se cumple coordenadaRandom <= coseno(valor) para ese thread.
     * @return Entero que retorna el contador. 
     */
    public Integer call(){
        int cont = 0;
        Random ran = new Random();
        double coordenadaRandom = ran.nextDouble(0,1);
        
        for(int index = INICIO; index<=FIN; index++){
            if(coordenadaRandom <= coseno(valor)){
                cont++;
            }
        }
        
        return cont;
    }
}