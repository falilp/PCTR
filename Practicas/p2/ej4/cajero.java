/**
 * Esta clase contiene los atributos y metodos de cajero
 * @author Rafael Leal Pardo
 * @version 2022
*/
public class cajero implements Runnable{
    public enum Accion {RETIRAR,IGRESAR};

    private cuentaCorriente cuenta;
    private final int numero;
    private int dinero;
    private Accion accion;
    
    /**
     * Metodo constructor parametrizado
     * @param cuen Objeto cuenta
     * @param num Numero de la cuenta
     * @param dine Dinero a reintegrar o depositar 
     * @param acc Acción a realizar (reintegrar o depositar)
     */
    public cajero(cuentaCorriente cuen,int num,int dine,Accion acc){
        this.cuenta = cuen;
        this.numero = num;
        this.dinero = dine;
        this.accion = acc;
    }

    /**
     * Metodo run que realizara el metodo reintegrar o depositar del objeto cuentaCorriente
     */
    public void run(){        
        switch (accion) {
            case RETIRAR:
                cuenta.deposito(numero,dinero);
                break;
            case IGRESAR:
                cuenta.reintegro(numero,dinero);
                break;
            default:
                break;
        }
    }
}
