/**
 * Esta clase contiene los atributos y metodos de cuentaCorriente
 * @author Rafael Leal Pardo
 * @version 2022
*/
public class cuentaCorriente{
    private int NumeroCuenta;
    private int saldo;
    
    /**
     * Metodo constructor parametrizado
     * @param Numero
     * @param sal
     */
    public cuentaCorriente(int Numero,int sal){
        this.NumeroCuenta = Numero;
        this.saldo = sal;
    }

    /**
     * Metodo que comprueba el numero de usuario y luego resta la cantidad indicada al saldo
     * @param numero
     * @param cantidad
     */
    public void deposito(int numero,int cantidad){
        if(NumeroCuenta == numero){
            saldo -= cantidad;
        }else{
            System.out.println("La cantidad es mayor que su saldo \n");
        }
    } 

    /**
     * Metodo que comprueba el numero de usuario y luego suma la cantidad indicada al saldo
     * @param numero
     * @param cantidad
     */
    public void reintegro(int numero,int cantidad){
        if(NumeroCuenta == numero){
            saldo += cantidad;
                
        }else{
            System.out.println("La cuenta no coincide \n");
        }
    } 

    /**
     * Metodo para regresar el saldo
     * @return Regresa el saldo de la cuenta Corriente
     */
    public int EstadoCuenta(){return saldo;}
}
