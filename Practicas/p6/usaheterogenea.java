
/**
 * Esta clase contiene los atributos y metodos de usaheterogenea con Thread
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class usaheterogenea extends Thread{
    private static final int nHebras = 8;
    private static final int nIteraciones = 1000;
    public heterogenea objetoHeterogenea;

    /**
     * Metodo constructor parametrizado.
     * @param objH Objeto de la clase heterogeneo.
     */
    public usaheterogenea(heterogenea objH){
        this.objetoHeterogenea = objH;
    }

    /**
     * Metodo run que los hilos incrementaran n y m llamando a las funciones de la clase heterogenea.
     */
    public void run(){
        for(int index = 0; index<nIteraciones; index++){
            objetoHeterogenea.IncrementoN();
            objetoHeterogenea.IncrementoM();
        }
    }

    /**
     * Metodo main que iniciara los valores y threads para luego ejecutarlos. 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        usaheterogenea vectorThreads[] = new usaheterogenea[nHebras];
        heterogenea objH = new heterogenea();

        Long inicioCrono = System.currentTimeMillis();

        for(int index=0; index<nHebras; index++){vectorThreads[index] = new usaheterogenea(objH);}
        for(int index=0; index<nHebras; index++){vectorThreads[index].start();}
        for(int index=0; index<nHebras; index++){vectorThreads[index].join();}
        
        Long finalizoCrono = System.currentTimeMillis();

        System.out.println("El numero n: "+ objH.DevolverN() +" y el numero m: "+ objH.DevolverM());
        System.out.println("El tiempo utilizado es de: "+ (finalizoCrono - inicioCrono) +" ms");
    }
}