/**
 * Esta clase contiene los atributos y metodos de redCajeros
 * @author Rafael Leal Pardo
 * @version 2022
 */
public class redCajeros{
    /**
     * Metodo main de la clase redCajeros donde se realizara la condición de concurso
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        cuentaCorriente cuenta = new cuentaCorriente(123,0);
        Runnable cajero1 = new cajero(cuenta,123,20,cajero.Accion.IGRESAR);
        Runnable cajero2 = new cajero(cuenta,123,20,cajero.Accion.RETIRAR);
        Runnable cajero3 = new cajero(cuenta,123,20,cajero.Accion.IGRESAR);
        Runnable cajero4 = new cajero(cuenta,123,20,cajero.Accion.RETIRAR);
        Runnable cajero5 = new cajero(cuenta,123,20,cajero.Accion.IGRESAR);
        Runnable cajero6 = new cajero(cuenta,123,20,cajero.Accion.RETIRAR);
        Runnable cajero7 = new cajero(cuenta,123,20,cajero.Accion.IGRESAR);
        Runnable cajero8 = new cajero(cuenta,123,20,cajero.Accion.RETIRAR);

        Thread h1 = new Thread(cajero1);
        Thread h2 = new Thread(cajero2);
        Thread h3 = new Thread(cajero3);
        Thread h4 = new Thread(cajero4);
        Thread h5 = new Thread(cajero5);
        Thread h6 = new Thread(cajero6);
        Thread h7 = new Thread(cajero7);
        Thread h8 = new Thread(cajero8);

        h1.start();h2.start();h3.start();h4.start();h5.start();h6.start();h7.start();h8.start();
        h1.join();h2.join();h3.join();h4.join();h5.join();h6.join();h7.join();h8.join();

        System.out.println("El resultado de la sumas de operaciones es: "+ cuenta.EstadoCuenta());
    }
}
