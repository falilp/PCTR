import mpi.*;

/**
 * Esta clase contiene los atributos y metodos de distributedIntegers mediante MPI.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class distributedIntegers{

    /**
     * Método para determinar si un número es primo.
     * @param value El numero a determinar si es primo.
     * @return true si el número es primo, false en caso contrario.
     */
    public static boolean esPrimo(long value){
        if(value < 2)return false;

        if(value == 2)return true;

        if(value == 3)return true;

        if((value%2) == 0 || (value%3) == 0)return false;

        for(int i=3; i<=Math.sqrt(value); i+=2){
            if((value%i) == 0)return false;
        }
        return true;
    }

    /**
     * Método main que realizará la distribución de enteros contando los numeros primos que se le asignaran a las ventanas de trabajo con
     * MPI que realizara mediante broadcast la realización de esta, con un posterior reduce para acumularlo en la variable total.
     * @param args argumentos de línea de comandos
     * @throws Exception si ocurre algún error al inicializar o finalizar MPI
     */
    public static void main(String[] args)throws Exception{
        long tamanioRango[] = new long[1];tamanioRango[0] = 10000000;
        //Inicializa el entorno MPI.
        MPI.Init(args);
        //Asigna el rango a la variable nTamanio.
        int nTamanio = MPI.COMM_WORLD.Rank();
        //Asigna el tamaño a nTamanio
        int numProceso = MPI.COMM_WORLD.Size();
        long nPrimos = 0;
        long total[] = new long[1];total[0] = 0;

        //Realiza el broadcast
        MPI.COMM_WORLD.Bcast(tamanioRango, 0, 1, MPI.LONG, 0);

        long inicio = nTamanio * tamanioRango[0]/numProceso;
        long fin = (nTamanio+1) * tamanioRango[0]/numProceso;

        //calcular los numeros primos
        for(long i=inicio; i<fin; ++i){
            if(esPrimo(i))++nPrimos;
        }

        long resultado[] = new long[1];
        resultado[0] = nPrimos;

        //acumular los numeros primos en la variable total
        MPI.COMM_WORLD.Reduce(resultado, 0, total, 0, 1, MPI.LONG, MPI.SUM, 0);

        if(nTamanio == 0){
            System.out.println("El numero total de los números primos es de: "+ total[0]);
        }

        MPI.Finalize();
    }
}