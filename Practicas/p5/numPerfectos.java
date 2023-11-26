/**
 * Esta clase contiene los atributos y metodos de numPerfectos
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class numPerfectos{
    private int NumeroLimite;
    private int contador;

    /**
     * Constructor parametrizado
     * @param limite Numero limite para buscar todos los numeros perfectos hasta el.
     */
    public numPerfectos(int limite){
        contador = 0;
        NumeroLimite = limite;
    }

    /**
     * Metodo que contara la cantidad de numeros perfectos que existe hasta el limite
     * @return Cantidad de numeros perfectos
     */
    public int ContarNumeros(){
        int aux = 0;
        for(int i=1; i<NumeroLimite; i++){
            aux = 0;
            for(int j=1; j<i; j++){
                if(i % j == 0){
                    aux += j;
                }
            }
            if(i == aux){
                contador++;
            }
        }

        return contador;
    }

    /**
     * Metodo main que realizara las llamadas y cronometrará los tiempos.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        numPerfectos numeros = new numPerfectos(2000);
        long InicioCrono = System.currentTimeMillis();
        System.out.println("El numero de Numeros Perfectos es de: "+ numeros.ContarNumeros());
        long FinalizoCrono = System.currentTimeMillis();
        System.out.println((FinalizoCrono - InicioCrono) + " ms");
    }
}
