/**
 * Esta clase contiene los atributos y metodos de prodEscalarParalelo
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class prodEscalarParalelo extends Thread{
    private static int tamanio = 1000000;
    public static int nHebras = 2;                          //Cambiar el numero de hilos aqui
    private static int[] productoParcial = new int [nHebras];
    private static int[] vector1;
    private static int[] vector2;
    private final int idHebra,Inicio,Fin;
    
    
    /**
     * Metodo constructor parametrizado
     * @param idH id de la hebra
     * @param inicio inicio de la hebra
     * @param fin fin de la hebra
     */
    public prodEscalarParalelo(int idH,int inicio,int fin){
        this.idHebra = idH;
        this.Inicio = inicio;
        this.Fin = fin;
    }

    /**
     * Metodo que inicializa los vectores
     */
    public static void InicializarVectores(){
        vector1 = new int[tamanio];
        vector2 = new int[tamanio];

        for(int i = 0; i<tamanio; i++){
            vector1[i] = 1000000; 
            vector2[i] = i+1;
        }
    }
    
    /**
     * Metodo que realizara el producto escalar de la hebra indica desde el indice Inicio hasta indice Fin
     * Que se ira sumando en productoParcial[idHebra] de la hebra que corresponda
     */
    public void run(){
        for(int i = Inicio; i<Fin; i++){
            productoParcial[idHebra] += (vector1[i] * vector2[i]);
        }
    }

    /**
     * Metodo que realizara el sumatorio del vector productoParcial
     * @return sumatorio (int)
     */
    public static int SumatorioProductoEscalar(){
        int sumatorio = 0;

        for(int i=0; i<nHebras; i++){
            sumatorio += productoParcial[i];
        }

        return sumatorio;
    }

    /**
     * Metodo main donde se crearan un vector de threads con el numero 
     * de nHebras que tiene la clase que se le asignara a cada objeto
     * de la clase con sus respectivos valores y se realizaran las llamadas y 
     * se cronometra los tiempos
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        int div = tamanio/nHebras;
        int resto = tamanio%nHebras;
        Thread vec[] = new Thread[nHebras];

        for(int i=0;i<nHebras;i++){
            if(i == nHebras-1){
                vec[i] = new prodEscalarParalelo(i,div*i,(div*(i+1))+resto);
            }else{
                vec[i] = new prodEscalarParalelo(i,div*i,div*(i+1));
            }
        }
        InicializarVectores();
        
        long InicioCrono = System.currentTimeMillis();
        
        for(int i=0;i<nHebras;i++){vec[i].start();}
        for(int i=0;i<nHebras;i++){vec[i].join();}

        long FinalizoCrono = System.currentTimeMillis();
        System.out.println((FinalizoCrono - InicioCrono) + " ms");
        System.out.println("El resultado del producto escalar es: "+ SumatorioProductoEscalar()  +" Este es el sumatorio");
    }
}   