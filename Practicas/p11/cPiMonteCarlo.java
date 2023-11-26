import java.rmi.Naming;

/**
 * Esta clase contiene los atributos y metodos de cPiMonteCarlo mediante RMI.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class cPiMonteCarlo{
    
    /**
     * Método main para acceder al servicio de cálculo de pi mediante el método de Monte Carlo.
     * @param args Argumentos de la línea de comandos
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        iPiMonteCarlo remotoMontecarlo = (iPiMonteCarlo)Naming.lookup("//localhost/80");

        remotoMontecarlo.calcular(100000);
    }
}