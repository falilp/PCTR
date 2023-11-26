import java.rmi.Naming;
import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos de cBonoLoto mediante RMI.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class cBonoLoto{

    /**
     * Método main para comprobar una apuesta de bonoloto.
     * @param args Argumentos de la línea de comandos.
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        Random ran = new Random();
        int nTanmanio = 6;
        int apuesta[] = new int[nTanmanio];

        for(int index=0; index<nTanmanio; ++index)apuesta[index] = ran.nextInt(1,49);

        iBonoLoto interfazRemotaBonoloto = (iBonoLoto)Naming.lookup("//localhost/Servidor");

        if(interfazRemotaBonoloto.comprobar(apuesta)){System.out.println("¡¡¡Ganaste la bonoloto!!!");
        }else{System.out.println("Has pedido la bonoloto.");}
    }
}