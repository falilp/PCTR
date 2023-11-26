/**
 * Esta clase contiene los atributos y metodos de prodCon
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class prodCon{
    private static int tamano = 10;
    private static int[] buffer = new int[tamano];
    private static int IntProductor = 0;
    private static int OutConsumidor = 0;
    public static int contador = 0;
    private static boolean Empty = true;
    private static boolean Full = false;

    /**
     * Metodo constructor no parametrizado.
     */
    public prodCon(){}

    /**
     * Método append que producirá un dato que introducirá en el buffer
     * en la posición IntProductor minetras no entre en la condición del if 
     * que producirá un wait hasta cumplir la condición de este.
     * @param data Entero random que será introducido en el buffer.
     */
    public synchronized void append(int data){
        if(contador == tamano){while(Full)try{wait();}catch(InterruptedException except){}}

        buffer[IntProductor] = data;
        System.out.println("Produce:"+ buffer[IntProductor]);
        contador++;
        IntProductor = (IntProductor + 1) % tamano;
    }

    /**
     * Método take que obtendrá un dato que introducirá en el buffer
     * en la posición OutConsumidor y esta posición pasará a 0,
     * minetras no entre en la condición del if 
     * que producirá un wait hasta cumplir la condición de este.
     * @return Entero random que será introducido en el buffer.
     */
    public synchronized int take(){
        if(contador == 0){while(Empty)try{wait();}catch(InterruptedException except){}}
        int data;

        data = buffer[OutConsumidor];
        System.out.println("Consume:"+ data);
        buffer[OutConsumidor] = 0;
        contador--;
        OutConsumidor=(OutConsumidor+1)%tamano;;

        return data;
    }

    /**
     * Metodo FinAppend que establecerá Empty de true a false y realizará
     * notifyAll para despertar a las otras hebras.(Tambien se le denomina signalC)
     */
    public synchronized void FinAppend(){
        /*if(tamano == IntProductor || tamano == OutConsumidor){
            IntProductor = tamano - 1;
            OutConsumidor = tamano - 1;
            Full = true;
            Empty = false;
        }else */
        if(Empty == true){
            Empty = false;
        }
        notifyAll();
    }

    /**
     * Metodo Fintake que establecerá Full de true a false y realizará
     * notifyAll para despertar a las otras hebras.(Tambien se le denomina signalC)
     */
    public synchronized void FinTake(){
        /*if(0 > IntProductor || 0 > OutConsumidor){
            IntProductor = 0;
            OutConsumidor = 0;
            Empty = true;
            Full = false;
        }else */
        if(Full == true){
            Full = false;
        }
        notifyAll();
    }
}