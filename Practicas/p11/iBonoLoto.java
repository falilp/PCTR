import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Esta clase contiene los atributos y metodos de iBonoLoto mediante RMI.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public interface iBonoLoto extends Remote{

    /**
     * Método para reiniciar el servidor de la lotería.
     * @throws RemoteException
     */
    public void reiniciarServidor()throws RemoteException;

    /**
     * Método para comprobar una apuesta de bonoloto.
     * @param apuesta Vector de enteros que representa la apuesta.
     * @return true si el número de la apuesta es ganadora, false si el número de la apuesta no es ganadora.
     * @throws RemoteException
     */
    public boolean comprobar(int apuesta[])throws RemoteException;
}