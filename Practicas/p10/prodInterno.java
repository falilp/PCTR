import mpi.*;

/**
 * Esta clase contiene los atributos y metodos de prodInterno mediante MPI.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class prodInterno{

    /**
     * Método main que realizará el producto escalar de dos vectores a través de 
     * MPI que realizara los send y recv para el paso de los valores y su posterior producto
     * Escalar.
     * @param args argumentos de línea de comandos
     * @throws Exception si ocurre algún error al inicializar o finalizar MPI
     */
    public static void main(String[] args)throws Exception{
        //Variable de vector de entero de un único  elemento.
        int ProdEscalar[] = new int[1];
        ProdEscalar[0] = 0;
        //Inicializa el entorno MPI
        MPI.Init(args);   
        //MPI le asigna a la variable ID respecto al rank.   
        int ID = MPI.COMM_WORLD.Rank();
        //MPI asigna a la variable nTamanio el size.
        int nTamanio = MPI.COMM_WORLD.Size();
        int tam = 4;
        int vec1[] = new int[tam];
        int vec2[] = new int[tam];

        /**
         * Si el ID es 0 se asignaran los elementos a los vectores y enviaran estos a través 
         * de send, una vez enviados recibirá el producto escalar mediante recv y lo mostrará por pantalla.
        */
        if(ID == 0){

            for(int index=1; index<tam; ++index){vec1[index] = index; vec2[index] = index;}

            MPI.COMM_WORLD.Send(vec1, 0, vec1.length, MPI.INT, 1, 9);
            MPI.COMM_WORLD.Send(vec2, 0, vec2.length, MPI.INT, 1, 10);

            MPI.COMM_WORLD.Recv(ProdEscalar, 0, 1, MPI.INT, 1, 11);

            System.out.println("Resultado del producto escalar de dos vectores es: "+ ProdEscalar);
        
        /**
         * Si el ID es 1 recibirá mediante recv los dos vectores y realizara el producto escalar de
         * estos, será devuelto dicho producto escalar mediante send.
         */
        }else if(ID == 1){

            MPI.COMM_WORLD.Recv(vec1, 0, vec1.length, MPI.INT, 0, 9);
            MPI.COMM_WORLD.Recv(vec2, 0, vec2.length, MPI.INT, 0, 10);

            for(int index=0; index<tam; ++index){ProdEscalar[0] += vec1[index] * vec2[index];}

            MPI.COMM_WORLD.Send(ProdEscalar, 0, 1, MPI.INT, 0, 11);
        
        /**
         * Mostrará un fallo en el caso que ID != 0 || 1
         */
        }else{ 

            System.out.println("No se Introdujo un ID valido");

        }

        MPI.Finalize();
    }
}