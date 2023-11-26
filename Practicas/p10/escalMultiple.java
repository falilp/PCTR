import mpi.*;
import java.util.Arrays;

/**
 * Esta clase contiene los atributos y metodos de escalMultiple mediante MPI.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class escalMultiple{

    /**
     * Método main que realizará el escalado multiple de dos vectores a través de 
     * MPI que realizara mediante broadcast la realización de esta.
     * @param args argumentos de línea de comandos
     * @throws Exception si ocurre algún error al inicializar o finalizar MPI
     */
    public static void main(String[] args)throws Exception{
        int tam = 10;
        int vec[] = new int[tam];
        //Inicializa el entorno MPI.
        MPI.Init(args);
        //Asigna el rango a la variable ID.
        int ID = MPI.COMM_WORLD.Rank();
        //Asigna el tamaño a nTamanio
        int nTamanio = MPI.COMM_WORLD.Size();

        //Al ser ID==0 realiza la asignación de los valores al vector master.
        if(ID == 0){
            for(int i=0; i<vec.length; ++i)vec[i] = i+1;
        }

        MPI.COMM_WORLD.Bcast(vec, 0, vec.length, MPI.INT, 0);

        ////Al ser ID!=0 realiza la asignación de los valores a los vectores slave.
        if(ID != 0){
            for (int i=0; i<vec.length; ++i)vec[i] *= ID;
        }
        
        //Imprime dichos vectores.
        System.out.println("El proceso ["+ ID +"]: "+ Arrays.toString(vec));

        MPI.Finalize();
    }
}