import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Esta clase contiene los atributos y metodos de iPiMonteCarlo mediante RMI.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public interface iPiMonteCarlo extends Remote{
    /**
     * Método para reiniciar los contadores de intentos y vueltas.
     * @throws RemoteException
     */
    public void reset()throws RemoteException;

    /**
     * Método para calcular una aproximación del valor de pi mediante el método de Monte Carlo.
     * @param nVueltas Entero del número de iteraciones para realizar el cálculo.
     * @throws RemoteException
     */
    public void calcular(int nVueltas)throws RemoteException;

    /**
     * Método para obtener la aproximación actual del valor de pi.
     * @return Double de la aproximación actual del valor de pi.
     * @throws RemoteException
     */
    public double aproximacionActual()throws RemoteException;
}